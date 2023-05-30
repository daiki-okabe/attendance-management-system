package models;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQueries({
    @NamedQuery(
        name = "getAllDivision",
        query = "SELECT d FROM Division AS d WHERE d.del_flg=0 ORDER BY d.division_id ASC"
    ),
    @NamedQuery(
            name = "getDivision_divisionId",
            query = "SELECT d FROM Division AS d WHERE d.del_flg=0 AND d.division_id=:div_id ORDER BY d.division_id ASC"
     )
})
@Table(name = "M_DIVIDION")
public class Division {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Column(name = "DIVIDION_ID")
    private Integer division_id;

    @Column(name = "DIVIDION_NAME", length = 200, nullable = false)
    private String division_name;

    @Column(name = "INP_USER", length = 200)
    private String inp_user;

    @Column(name = "INP_DATE")
    private LocalDateTime inp_date;

    @Column(name = "UPD_USER", length = 200)
    private String upd_user;

    @Column(name = "UPD_DATE")
    private LocalDateTime upd_date;

    @Column(name = "DEL_FLG")
    private Integer del_flg;

    public Integer getDivision_id() {
        return division_id;
    }

    public void setDivision_id(Integer division_id) {
        this.division_id = division_id;
    }

    public String getDivision_name() {
        return division_name;
    }

    public void setDivision_name(String division_name) {
        this.division_name = division_name;
    }

    public String getInp_user() {
        return inp_user;
    }

    public void setInp_user(String inp_user) {
        this.inp_user = inp_user;
    }

    public LocalDateTime getInp_date() {
        return inp_date;
    }

    public void setInp_date(LocalDateTime inp_date) {
        this.inp_date = inp_date;
    }

    public String getUpd_user() {
        return upd_user;
    }

    public void setUpd_user(String upd_user) {
        this.upd_user = upd_user;
    }

    public LocalDateTime getUpd_date() {
        return upd_date;
    }

    public void setUpd_date(LocalDateTime upd_date) {
        this.upd_date = upd_date;
    }

    public Integer getDel_flg() {
        return del_flg;
    }

    public void setDel_flg(Integer del_flg) {
        this.del_flg = del_flg;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


}