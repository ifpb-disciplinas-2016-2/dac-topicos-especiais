package ifpb.ads.dac.service;

import java.util.function.BiConsumer;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/04/2017, 08:36:54
 */
public class Auditoria {

    @AroundInvoke
    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Object interceptador(InvocationContext ic) throws Exception {
        long inicio = System.currentTimeMillis();
        Object retornoDoMetodoDeNegocio = ic.proceed();
        long fim = System.currentTimeMillis();
        String nomeMetodo = ic.getMethod().getName();
        System.out.println("demorou: " + (fim - inicio) + " no método: " + nomeMetodo);
        System.out.println("Auditoria.class");

        ic.getContextData().forEach((t, u) -> {
            System.out.println(String.format("chave:%s e valor: %s", t, u.toString()));
        }
        );
        return retornoDoMetodoDeNegocio;
    }
}
