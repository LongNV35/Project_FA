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
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "FAMS_G2")
public class ClassBath {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "class_id")
  private int classId;

  @Column(name = "class_name")
  private String className;

  @Column(name = "planned_traineeNumber")
  private int plannedTraineeNumber;

  @Column(name = "accepted_traineeNumber")
  private int acceptedTraineeNumber;

  @Column(name = "actual_traineeNumber")
  private int actualTraineeNumber;

  private String status;

  private String skill;

  @OneToOne(mappedBy = "classBath")
  private ClassAdmin classAdmin;

  @ManyToOne
  @JoinColumn(name = "location_id", nullable = false)
  private Location location;

}
