package ifpb.ads.dac.aop;

import ifpb.ads.dac.domain.Aluno;
import java.time.LocalDateTime;
import javax.persistence.PostLoad;
import javax.persistence.PostPersist;
import javax.persistence.PrePersist;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/04/2017, 08:01:51
 */
public class LogEntities {

    @PrePersist
    public void antesDePersistir(Object object) {
        String value = String.format("aluno: %s, salvo em: %s", object.toString(),
                LocalDateTime.now().toString());
        System.out.println(value);
    }

    @PostPersist
    public void aposPersistir(Object object) {
        String value = String.format("aluno: %s, salvo em: %s", object.toString(),
                LocalDateTime.now().toString());
        System.out.println(value);
    }

    @PostLoad
    public void aposCarregar(Object object) {
        String value = String.format("aluno: %s, carregado em: %s", object.toString(),
                LocalDateTime.now().toString());
        System.out.println(value);
    }
}
