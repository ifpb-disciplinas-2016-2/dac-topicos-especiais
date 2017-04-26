package ifpb.ads.dac.domain;

import ifpb.ads.dac.web.LogEntities;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/04/2017, 07:28:03
 */
@Entity
//@EntityListeners({LogEntities.class})
public class Aluno implements Serializable{

    @Id
    @GeneratedValue
    private int id;
    private String nome;

    public Aluno() {
    }

    public Aluno(String nome) {
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
    
    
//    @PrePersist
//    public void antesDePersistir(){
//        String value = String.format("aluno: %d, salvo em: %s", this.getId(), 
//                LocalDateTime.now().toString());
//        System.out.println(value);
//    }
//    @PostPersist
//    public void aposPersistir(){
//        String value = String.format("aluno: %d, salvo em: %s", this.getId(), 
//                LocalDateTime.now().toString());
//        System.out.println(value);
//    }
//    
//    @PostLoad
//    public void aposCarregar(){
//        String value = String.format("aluno: %d, carregado em: %s", this.getId(), 
//                LocalDateTime.now().toString());
//        System.out.println(value);
//    }

    @Override
    public String toString() {
        return "Aluno{" + "id=" + id + ", nome=" + nome + '}';
    }
    
}
