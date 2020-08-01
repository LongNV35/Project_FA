package fa.fams.service.serviceimpl;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import fa.fams.common.log.Log4J;
import fa.fams.model.Candidate;

@Component
public class SendEmailServiceImpl {

  @Autowired
  public JavaMailSender javaMailSender;

  /**
   * this fuction to send email to candidate.
   * 
   * @param candidate - param take off from view
   * @param level     - param take off from view
   * @throws MessagingException - exception canbe met when email
   */
  public boolean sendEmail(Candidate candidate, String level) {
    MimeMessage msg = javaMailSender.createMimeMessage();
    MimeMessageHelper helper;
    try {
      helper = new MimeMessageHelper(msg, true);
      helper.setTo(candidate.getCandidateProfile().getEmail());
      helper.setFrom("hn19frjava08g2@gmail.com");
      helper.setSubject(new StringBuffer("[FA MANAGEMENT]: ")
          .append(candidate.getCandidateProfile().getFullName()).append(" [")
          .append(candidate.getCandidateID())
          .append("] has been transferred to ").append(level + ".").toString());
      helper.setText(new StringBuffer("<p>Dear [Candidate] [").append(level)
          .append("] <br/> ")
          .append(candidate.getCandidateProfile().getFullName()).append(" [")
          .append(candidate.getCandidateID())
          .append("] has been transferred to " + level)
          .append(".<br/>To view the Candidate details, please click <a "
              + "style='color: red;' href='google.com'>here</a>.<br/>"
              + "Sincerely,<br/>Application Admin.<br/><i>Note: "
              + "This is an auto-generated email, please do not reply.</i></p>")
          .toString(), true);
      javaMailSender.send(msg);
    } catch (MessagingException e) {
      Log4J.getLogger().info(e);
      return false;
    }

    return true;
  }
}