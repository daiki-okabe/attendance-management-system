package views;

import java.util.ArrayList;
import java.util.List;

import models.User;


/**
 * 従業員データのDTOモデル⇔Viewモデルの変換を行うクラス
 *
 */
public class UserConverter {

    /**
     * ViewモデルのインスタンスからDTOモデルのインスタンスを作成する
     * @param ev UserViewのインスタンス
     * @return Userのインスタンス
     */
    public static User toModel(UserView ev) {
        if(ev == null) {
            return null;
        }

         User newUser =new User();
         newUser.setUser_id(ev.getUser_id());
         newUser.setUser_name(ev.getUser_name());
         newUser.setUser_role(ev.getUser_role());
         newUser.setUser_class(ev.getUser_class());
         newUser.setDept_id(ev.getDept_id());
         newUser.setInp_date(ev.getInp_date());
         newUser.setInp_user(ev.getInp_user());
         newUser.setUpd_date(ev.getUpd_date());
         newUser.setUpd_user(ev.getUpd_user());

        return newUser;

    }

    /**
     * DTOモデルのインスタンスからViewモデルのインスタンスを作成する
     * @param e Userのインスタンス
     * @return UserViewのインスタンス
     */
    public static UserView toView(User e) {

        if(e == null) {
            return null;
        }
        UserView newUserView =new UserView();
        newUserView.setUser_id(e.getUser_id());
        newUserView.setUser_name(e.getUser_name());
        newUserView.setUser_role(e.getUser_role());
        newUserView.setUser_class(e.getUser_class());
        newUserView.setDept_id(e.getDept_id());
        newUserView.setInp_date(e.getInp_date());
        newUserView.setInp_user(e.getInp_user());
        newUserView.setUpd_date(e.getUpd_date());
        newUserView.setUpd_user(e.getUpd_user());

       return newUserView;
    }

    /**
     * DTOモデルのリストからViewモデルのリストを作成する
     * @param list DTOモデルのリスト
     * @return Viewモデルのリスト
     */
    public static List<UserView> toViewList(List<User> list) {
        List<UserView> evs = new ArrayList<>();

        for (User e : list) {
            evs.add(toView(e));
        }

        return evs;
    }

    /**
     * Viewモデルの全フィールドの内容をDTOモデルのフィールドにコピーする
     * @param e DTOモデル(コピー先)
     * @param ev Viewモデル(コピー元)
     */
    public static void copyViewToModel(User e, UserView ev) {
        ev.setUser_id(ev.getUser_id());
        ev.setUser_name(ev.getUser_name());
        ev.setUser_role(ev.getUser_role());
        ev.setUser_class(ev.getUser_class());
        ev.setDept_id(ev.getDept_id());
        ev.setInp_date(ev.getInp_date());
        ev.setInp_user(ev.getInp_user());
        ev.setUpd_date(ev.getUpd_date());
        ev.setUpd_user(ev.getUpd_user());

    }

}
