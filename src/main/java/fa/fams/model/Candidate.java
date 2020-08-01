package fa.fams.model;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import lombok.ToString;

@Entity
@ToString
@Table(schema = "FAMS_G2")
public class Candidate {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "candidate_id")
  private int candidateID;

  @Column(name = "application_date")
  private LocalDate applicationDate;

  private String status;

  @Column(columnDefinition = "TEXT")
  private String history;

  private String remarks;

  @OneToMany(mappedBy = "candidate")
  private List<EntryTest> listEntryTest;

  @ManyToOne
  @JoinColumn(name = "channel_id", nullable = false)
  private Channel channel;

  @OneToMany(mappedBy = "candidate")
  private List<InterView> listInterview;

  @OneToOne(mappedBy = "candidate")
  private CandidateProfile candidateProfile;

  @ManyToOne
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

  public Candidate() {
    super();
  }

  public Candidate(int candidateID, LocalDate applicationDate,
      String status, String history, Channel channel,
      Location location) {
    super();
    this.candidateID = candidateID;
    this.applicationDate = applicationDate;
    this.status = status;
    this.history = history;
    this.channel = channel;
    this.location = location;
  }

  public Candidate(int candidateID, LocalDate applicationDate,
      String status, String history, String remarks,
      Channel channel, Location location) {
    super();
    this.candidateID = candidateID;
    this.applicationDate = applicationDate;
    this.status = status;
    this.history = history;
    this.remarks = remarks;
    this.channel = channel;
    this.location = location;
  }

  public Candidate(LocalDate applicationDate, String status,
      String history, Channel channel, Location location) {
    super();
    this.applicationDate = applicationDate;
    this.status = status;
    this.history = history;
    this.channel = channel;
    this.location = location;
  }

  public int getCandidateID() {
    return candidateID;
  }

  public void setCandidateID(int candidateID) {
    this.candidateID = candidateID;
  }

  public LocalDate getApplicationDate() {
    return applicationDate;
  }

  public void setApplicationDate(LocalDate applicationDate) {
    this.applicationDate = applicationDate;
  }

  public String getStatus() {
    return status;
  }

  public void setStatus(String status) {
    this.status = status;
  }

  public String getHistory() {
    return history;
  }

  public void setHistory(String history) {
    this.history = history;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public List<EntryTest> getListEntryTest() {
    return listEntryTest;
  }

  public void setListEntryTest(List<EntryTest> listEntryTest) {
    this.listEntryTest = listEntryTest;
  }

  public Channel getChannel() {
    return channel;
  }

  public void setChannel(Channel channel) {
    this.channel = channel;
  }

  public List<InterView> getListInterview() {
    return listInterview;
  }

  public void setListInterview(List<InterView> listInterview) {
    this.listInterview = listInterview;
  }

  public CandidateProfile getCandidateProfile() {
    return candidateProfile;
  }

  public void setCandidateProfile(
      CandidateProfile candidateProfile) {
    this.candidateProfile = candidateProfile;
  }

  public Location getLocation() {
    return location;
  }

  public void setLocation(Location location) {
    this.location = location;
  }

  public Candidate(LocalDate applicationDate, String status,
      String history, String remarks,
      List<EntryTest> listEntryTest, Channel channel,
      List<InterView> listInterview,
      CandidateProfile candidateProfile, Location location) {
    super();
    this.applicationDate = applicationDate;
    this.status = status;
    this.history = history;
    this.remarks = remarks;
    this.listEntryTest = listEntryTest;
    this.channel = channel;
    this.listInterview = listInterview;
    this.candidateProfile = candidateProfile;
    this.location = location;
  }
}
