package ifpb.ads.dac.service;

import ifpb.ads.dac.aop.Auditoria;
import ifpb.ads.dac.domain.Aluno;
import java.time.LocalDateTime;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptors;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/04/2017, 07:33:50
 */
@Stateless
@Interceptors({Auditoria.class})
public class ServiceAluno {

    @PersistenceContext
    private EntityManager em;

    @EJB
    private NotificadorDeAluno notificador;
    
    public void salvarAluno(Aluno aluno) {
        em.persist(aluno);
        notificador.alunoCadastrado(aluno);
    }

    public List<Aluno> listarTodos() {
        return em.createQuery("SELECT a FROM Aluno a", Aluno.class).getResultList();
    }

    @AroundInvoke
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Object interceptador(InvocationContext ic) throws Exception {
        long inicio = System.currentTimeMillis();
        Object retornoDoMetodoDeNegocio = ic.proceed();
        long fim = System.currentTimeMillis();
        String nomeMetodo = ic.getMethod().getName();
        System.out.println("demorou: " + (fim - inicio) + " no método: " + nomeMetodo);
        // tranferindo informações entre interceptadores
        ic.getContextData().put("results", 1);
        return retornoDoMetodoDeNegocio;
    }

//    @Schedule(second = "5", minute = "*", hour = "*")
//    public void enviarRelatorios() {
//        System.out.println(String.format("tamanho : %d, em : %s", listarTodos().size(), 
//                LocalDateTime.now().toString()));
//    }
}
