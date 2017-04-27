package ifpb.ads.dac.service;

import ifpb.ads.dac.domain.Aluno;
import java.util.Enumeration;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.ejb.Schedule;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Queue;
import javax.jms.TextMessage;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/04/2017, 21:22:38
 */
@Stateless
public class NotificadorDeAluno {

    @Inject
    @JMSConnectionFactory("jms/dac/ConnectionFactory")
    private JMSContext context;
    @Resource(lookup = "jms/dac/Queue")
    private Queue queue;

    public void alunoCadastrado(Aluno aluno) {
        try {
            String id = String.valueOf(aluno.getId());
            JMSProducer createProducer
                    = context.createProducer();
            TextMessage createTextMessage
                    = context.createTextMessage(id);
            createTextMessage.setBooleanProperty("cursando", true);
            createTextMessage.setJMSReplyTo(queue);
            createProducer.send(queue, createTextMessage);
            System.out.println("enviando mensagem..." + id);
        } catch (JMSException ex) {
            Logger.getLogger(NotificadorDeAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Schedule(hour = "*", minute = "*/2", second = "*")
    public void alunosAprovados() {
        System.out.println(lendoMensagens());
        JMSConsumer createConsumer = context.createConsumer(queue, "cursando=false");
        String receiveBody = createConsumer.receiveBody(String.class);
        //Aqui deve ser tratar a informação
        System.out.println("receiveBody = " + receiveBody);
    }

    private String lendoMensagens() {
        try {
            int numMessages = 0;
            for (Enumeration queueEnumeration = context.createBrowser(queue).getEnumeration(); queueEnumeration.hasMoreElements();) {
                System.out.println("Recebendo: " + queueEnumeration.nextElement());
                numMessages++;
            }
            return "Faltam : " + numMessages + " mensagens a ser lidas";
        } catch (JMSException ex) {
            Logger.getLogger(NotificadorDeAluno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return "sem msg";
    }

}
