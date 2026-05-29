package springdatajdbc01;

package springdatajdbc01;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table("dept")
public class Dept {
    @Id
    private int deptno;
    private String dname;
    private String loc;

    public Dept(String dname, String loc) {
        this.dname = dname;
        this.loc = loc;
    }
}
