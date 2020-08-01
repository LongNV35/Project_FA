package fa.fams.model;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
@Entity
@Table(schema = "FAMS_G2")
public class Faculty {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "faculty_id")
  private int facultyId;

  @Column(name = "faculty_name")
  @NonNull
  private String facultyName;

  @Column(name = "remarks", nullable = true)
  private String remarks;

  @OneToMany(mappedBy = "faculty")
  private List<CandidateProfile> candidateProfile;
}
