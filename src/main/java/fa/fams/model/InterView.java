package fa.fams.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(schema = "FAMS_G2")
public class InterView {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "interview_id")
  private int interviewId;

  @Column(name = "interview_time")
  private int interviewTime;

  @Column(name = "interview_date")
  private LocalDate interviewDate;

  @Column(name = "interviewer")
  private String interviewer;

  @Column(name = "comments")
  private String comments;

  @Column(name = "remarks")
  private String remarks;

  @Column(name = "result")
  private String result;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  @OneToOne
  @JoinColumn(name = "offer_id")
  private Offer offer;

  public InterView() {
    super();
  }

  public InterView(int interviewId, int interviewTime, LocalDate interviewDate,
      String interviewer, String comments, String remarks, String result,
      Candidate candidate, Offer offer) {
    super();
    this.interviewId = interviewId;
    this.interviewTime = interviewTime;
    this.interviewDate = interviewDate;
    this.interviewer = interviewer;
    this.comments = comments;
    this.remarks = remarks;
    this.result = result;
    this.candidate = candidate;
    this.offer = offer;
  }

  public int getInterviewId() {
    return interviewId;
  }

  public void setInterviewId(int interviewId) {
    this.interviewId = interviewId;
  }

  public int getInterviewTime() {
    return interviewTime;
  }

  public void setInterviewTime(int interviewTime) {
    this.interviewTime = interviewTime;
  }

  public LocalDate getInterviewDate() {
    return interviewDate;
  }

  public void setInterviewDate(LocalDate interviewDate) {
    this.interviewDate = interviewDate;
  }

  public String getInterviewer() {
    return interviewer;
  }

  public void setInterviewer(String interviewer) {
    this.interviewer = interviewer;
  }

  public String getComments() {
    return comments;
  }

  public void setComments(String comments) {
    this.comments = comments;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }

  public Offer getOffer() {
    return offer;
  }

  public void setOffer(Offer offer) {
    this.offer = offer;
  }
}
