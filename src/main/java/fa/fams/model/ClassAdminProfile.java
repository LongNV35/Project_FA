package fa.fams.model;

import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "FAMS_G2")
public class ClassAdminProfile {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "class_admin_profileID")
  private int classAdminProfileId;

  private String fullName;
  private LocalDate dateOfBirth;
  private boolean gender;
  private String phone;
  private String email;
  private String remarks;

  @OneToOne
  @JoinColumn(name = "class_adminid", nullable = false, unique = true)
  private ClassAdmin classAdmin;
}
