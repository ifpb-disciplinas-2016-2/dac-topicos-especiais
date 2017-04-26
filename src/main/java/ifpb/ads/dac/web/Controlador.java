package ifpb.ads.dac.web;

import ifpb.ads.dac.domain.Aluno;
import ifpb.ads.dac.service.ServiceAluno;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/04/2017, 07:32:58
 */
@Named
@RequestScoped
public class Controlador {

    @Inject
    private ServiceAluno service;

    private Aluno aluno = new Aluno();
    private List<Aluno> alunos;

    public Controlador() {

    }

    @PostConstruct
    public void init() {
        alunos = service.listarTodos();
    }

    public String salvar() {
        service.salvarAluno(aluno);
        aluno = new Aluno();
        alunos = service.listarTodos();
        return null;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    public List<Aluno> getTodosOsAlunos() {
        return this.alunos;
    }

}
