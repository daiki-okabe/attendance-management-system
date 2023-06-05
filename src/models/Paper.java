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
        name = "getAllPaper",
        query = "SELECT u FROM User AS u WHERE u.del_flg=0 ORDER BY u.user_id ASC"
    ),
    @NamedQuery(
            name = "selectPaper_PaperId",
            query = "SELECT p FROM Paper AS p WHERE p.del_flg=0 AND p.paper_id=:id ORDER BY p.paper_id ASC"
    )
})
@Table(name = "M_PAPER")
public class Paper {
    @Id
    @Column(name = "PAPER_ID", length = 10)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer paper_id;

    @Column(name = "PAPER_NAME", length = 200)
    private String paper_name;

    @Column(name = "URL", length = 200)
    private String url;

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

    public Integer getPaper_id() {
        return paper_id;
    }

    public void setPaper_id(Integer paper_id) {
        this.paper_id = paper_id;
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

    public String getPaper_name() {
        return paper_name;
    }

    public void setPaper_name(String paper_name) {
        this.paper_name = paper_name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}