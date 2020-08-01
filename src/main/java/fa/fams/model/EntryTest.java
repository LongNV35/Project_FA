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
import javax.persistence.Table;
import lombok.EqualsAndHashCode;



@EqualsAndHashCode
@Entity
@Table(schema = "FAMS_G2")
public class EntryTest {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "test_id")
  private int testId;

  private int time;

  private LocalDate date;

  @Column(name = "language_valuator")
  private String languageValuator;

  @Column(name = "language_result")
  private int languageResult;

  @Column(name = "technical_valuator")
  private String technicalValuator;

  @Column(name = "technical_result")
  private int technicalResult;

  private String result;

  private String remarks;

  @ManyToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "candidate_id")
  private Candidate candidate;

  public EntryTest() {
    super();
  }

  public EntryTest(int testId, int time, LocalDate date,
      String languageValuator, int languageResult, String technicalValuator,
      int technicalResult, String result, String remarks, Candidate candidate) {
    super();
    this.testId = testId;
    this.time = time;
    this.date = date;
    this.languageValuator = languageValuator;
    this.languageResult = languageResult;
    this.technicalValuator = technicalValuator;
    this.technicalResult = technicalResult;
    this.result = result;
    this.remarks = remarks;
    this.candidate = candidate;
  }

  public int getTestId() {
    return testId;
  }

  public void setTestId(int testId) {
    this.testId = testId;
  }

  public int getTime() {
    return time;
  }

  public void setTime(int time) {
    this.time = time;
  }

  public LocalDate getDate() {
    return date;
  }

  public void setDate(LocalDate date) {
    this.date = date;
  }

  public String getLanguageValuator() {
    return languageValuator;
  }

  public void setLanguageValuator(String languageValuator) {
    this.languageValuator = languageValuator;
  }

  public int getLanguageResult() {
    return languageResult;
  }

  public void setLanguageResult(int languageResult) {
    this.languageResult = languageResult;
  }

  public String getTechnicalValuator() {
    return technicalValuator;
  }

  public void setTechnicalValuator(String technicalValuator) {
    this.technicalValuator = technicalValuator;
  }

  public int getTechnicalResult() {
    return technicalResult;
  }

  public void setTechnicalResult(int technicalResult) {
    this.technicalResult = technicalResult;
  }

  public String getResult() {
    return result;
  }

  public void setResult(String result) {
    this.result = result;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }
}
