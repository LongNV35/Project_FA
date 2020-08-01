package fa.fams.common.valueobjects;

public class MessageVo {
  private String messageOfCandidate;
  private String messageOfEntryTest;
  private String messageOfInterview;

  
  public MessageVo() {
    super();
  }

  public MessageVo(String messageOfCandidate, String messageOfEntryTest,
      String messageOfInterview) {
    super();
    this.messageOfCandidate = messageOfCandidate;
    this.messageOfEntryTest = messageOfEntryTest;
    this.messageOfInterview = messageOfInterview;
  }

  public String getMessageOfCandidate() {
    return messageOfCandidate;
  }

  public void setMessageOfCandidate(String messageOfCandidate) {
    this.messageOfCandidate = messageOfCandidate;
  }

  public String getMessageOfEntryTest() {
    return messageOfEntryTest;
  }

  public void setMessageOfEntryTest(String messageOfEntryTest) {
    this.messageOfEntryTest = messageOfEntryTest;
  }

  public String getMessageOfInterview() {
    return messageOfInterview;
  }

  public void setMessageOfInterview(String messageOfInterview) {
    this.messageOfInterview = messageOfInterview;
  }

}
