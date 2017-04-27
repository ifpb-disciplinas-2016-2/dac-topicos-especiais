package ifpb.ads.dac.service;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.Destination;
import javax.jms.JMSConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/04/2017, 21:37:34
 */
@MessageDriven(
        activationConfig = {
            @ActivationConfigProperty(propertyName = "destinationType",
                    propertyValue = "javax.jms.Queue")
            ,
        @ActivationConfigProperty(propertyName = "destinationLookup",
                    propertyValue = "jms/dac/Queue")
            ,
            @ActivationConfigProperty(propertyName = "messageSelector",
                    propertyValue = "cursando=true")
        })
public class AvaliarNotasDosAlunos implements MessageListener {

    @Inject
    @JMSConnectionFactory("jms/dac/ConnectionFactory")
    private JMSContext context;

    @Override
    public void onMessage(Message message) {
        try {
            String idDoAluno = message.getBody(String.class);
            JMSProducer createProducer
                    = context.createProducer();
            TextMessage createTextMessage
                    = context.createTextMessage(idDoAluno);
            createTextMessage.setBooleanProperty("cursando", false);
            Destination temp =  message.getJMSReplyTo();
            createProducer.send(temp, createTextMessage);
        } catch (JMSException ex) {
            Logger.getLogger(AvaliarNotasDosAlunos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
