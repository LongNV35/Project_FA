package fa.fams.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "FAMS_G2")
public class Trainee {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "trainee_id")
  private int traineeId;

  private String remarks;

  @ManyToOne
  @JoinColumn(name = "status_id")
  private Status status;

  @ManyToOne
  @JoinColumn(name = "class_id")
  @NonNull
  private ClassBath classBath;

  @OneToOne
  @JoinColumn(name = "candidate_profileId")
  @NonNull
  private CandidateProfile candidateProfile;

  public Trainee(Status status, CandidateProfile candidateProfile) {
    super();
    this.status = status;
    this.candidateProfile = candidateProfile;
  }

}
