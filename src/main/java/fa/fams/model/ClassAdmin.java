package fa.fams.model;

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
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(schema = "FAMS_G2")
public class ClassAdmin {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "class_adminID")
  private int classAdminId;

  private String remarks;

  @OneToOne
  @JoinColumn(name = "class_id", unique = true)
  private ClassBath classBath;

  @OneToOne(mappedBy = "classAdmin")
  private ClassAdminProfile classAdminProfile;
}
