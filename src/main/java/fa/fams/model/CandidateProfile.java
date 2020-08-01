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
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(schema = "FAMS_G2")
public class CandidateProfile {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "candidate_profileId")
  private int candidateProfileId;

  @Column(name = "full_name", nullable = false)
  private String fullName;

  @Column(name = "account")
  private String account;

  @Column(name = "date_ofBirth")
  @JsonDeserialize(using = LocalDateDeserializer.class)
  @JsonSerialize(using = LocalDateSerializer.class)
  private LocalDate dateOfBirth;

  @Column(name = "gender", nullable = false)
  private Boolean gender;

  @Column(name = "graduation_year")
  private LocalDate graduationYear;

  @Column(name = "phone", unique = true, nullable = false)
  private String phone;

  @Column(name = "email", unique = true, nullable = false)
  private String email;

  private String type;

  private String skill;

  @Column(name = "foreign_language")
  private String foreignLanguage;

  private String level;

  private String cv;

  @Column(name = "allocation_status")
  private String allocationStatus;

  @Column(name = "remarks")
  private String remarks;

  @ManyToOne
  @JoinColumn(name = "university_id", nullable = false)
  private University university;

  @ManyToOne
  @JoinColumn(name = "faculty_id", nullable = false)
  private Faculty faculty;

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "candidate_id", nullable = false, unique = true)
  private Candidate candidate;

  public CandidateProfile() {
    super();
  }

  public CandidateProfile(int candidateProfileId, String fullName,
      String account, LocalDate dateOfBirth, Boolean gender,
      LocalDate graduationYear, String phone, String email, String type,
      String skill, String foreignLanguage, String level, String cv,
      String allocationStatus, String remarks, University university,
      Faculty faculty, Candidate candidate) {
    this.candidateProfileId = candidateProfileId;
    this.fullName = fullName;
    this.account = account;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.graduationYear = graduationYear;
    this.phone = phone;
    this.email = email;
    this.type = type;
    this.skill = skill;
    this.foreignLanguage = foreignLanguage;
    this.level = level;
    this.cv = cv;
    this.allocationStatus = allocationStatus;
    this.remarks = remarks;
    this.university = university;
    this.faculty = faculty;
    this.candidate = candidate;
  }

  public CandidateProfile(String fullName, String account,
      LocalDate dateOfBirth, Boolean gender, LocalDate graduationYear,
      String phone, String email, String type, String skill,
      String foreignLanguage, String level, String cv, String allocationStatus,
      String remarks, University university, Faculty faculty,
      Candidate candidate) {
    super();
    this.fullName = fullName;
    this.account = account;
    this.dateOfBirth = dateOfBirth;
    this.gender = gender;
    this.graduationYear = graduationYear;
    this.phone = phone;
    this.email = email;
    this.type = type;
    this.skill = skill;
    this.foreignLanguage = foreignLanguage;
    this.level = level;
    this.cv = cv;
    this.allocationStatus = allocationStatus;
    this.remarks = remarks;
    this.university = university;
    this.faculty = faculty;
    this.candidate = candidate;
  }

  public int getCandidateProfileId() {
    return candidateProfileId;
  }

  public void setCandidateProfileId(int candidateProfileId) {
    this.candidateProfileId = candidateProfileId;
  }

  public String getFullName() {
    return fullName;
  }

  public void setFullName(String fullName) {
    this.fullName = fullName;
  }

  public String getAccount() {
    return account;
  }

  public void setAccount(String account) {
    this.account = account;
  }

  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }

  public Boolean getGender() {
    return gender;
  }

  public void setGender(Boolean gender) {
    this.gender = gender;
  }

  public LocalDate getGraduationYear() {
    return graduationYear;
  }

  public void setGraduationYear(LocalDate graduationYear) {
    this.graduationYear = graduationYear;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getSkill() {
    return skill;
  }

  public void setSkill(String skill) {
    this.skill = skill;
  }

  public String getForeignLanguage() {
    return foreignLanguage;
  }

  public void setForeignLanguage(String foreignLanguage) {
    this.foreignLanguage = foreignLanguage;
  }

  public String getLevel() {
    return level;
  }

  public void setLevel(String level) {
    this.level = level;
  }

  public String getCv() {
    return cv;
  }

  public void setCv(String cv) {
    this.cv = cv;
  }

  public String getAllocationStatus() {
    return allocationStatus;
  }

  public void setAllocationStatus(String allocationStatus) {
    this.allocationStatus = allocationStatus;
  }

  public String getRemarks() {
    return remarks;
  }

  public void setRemarks(String remarks) {
    this.remarks = remarks;
  }

  public University getUniversity() {
    return university;
  }

  public void setUniversity(University university) {
    this.university = university;
  }

  public Faculty getFaculty() {
    return faculty;
  }

  public void setFaculty(Faculty faculty) {
    this.faculty = faculty;
  }

  public Candidate getCandidate() {
    return candidate;
  }

  public void setCandidate(Candidate candidate) {
    this.candidate = candidate;
  }

}
