package services;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.NoResultException;

import constants.JpaConst;
import models.User;
import utils.EncryptUtil;
import views.UserConverter;
import views.UserView;

public class UserService  extends ServiceBase {
     public List<UserView> getPerPage(int page) {
            List<User> Users = em.createNamedQuery("M_USER.getAllUsers", User.class)
                    .setFirstResult(JpaConst.ROW_PER_PAGE * (page - 1))
                    .setMaxResults(JpaConst.ROW_PER_PAGE)
                    .getResultList();

            return UserConverter.toViewList(Users);
        }

        /**
         * 従業員テーブルのデータの件数を取得し、返却する
         * @return 従業員テーブルのデータの件数
         */
        public long countAll() {
            long empCount = (long) em.createNamedQuery("M_USER.count", Long.class)
                    .getSingleResult();

            return empCount;
        }

        /**
         * 社員ID、パスワードを条件に取得したデータをUserViewのインスタンスで返却する
         * @param uid 社員ID
         * @param plainPass パスワード文字列
         * @param pepper pepper文字列
         * @return 取得データのインスタンス 取得できない場合null
         */
        public UserView findOne(String uid, String plainPass, String pepper) {
            User e = null;
            try {
                //パスワードのハッシュ化
                String pass = EncryptUtil.getPasswordEncrypt(plainPass, pepper);

                //社員IDとハッシュ化済パスワードを条件に未削除の従業員を1件取得する
                e = em.createNamedQuery("M_USER.getByUser_idAndPass", User.class)
                        .setParameter("USER_ID", uid)
                        .setParameter("PASSWOED", pass)
                        .getSingleResult();

            } catch (NoResultException ex) {
            }

            return UserConverter.toView(e);

        }

        /**
         * idを条件に取得したデータをUserViewのインスタンスで返却する
         * @param id
         * @return 取得データのインスタンス
         */
        public UserView findOne(int id) {
            User e = findOneInternal(id);
            return UserConverter.toView(e);
        }

        /**
         * 社員IDを条件に該当するデータの件数を取得し、返却する
         * @param uid 社員ID
         * @return 該当するデータの件数
         */
        public long countByUser_id(String uid) {

            //指定した社員IDを保持する従業員の件数を取得する
            long Users_count = (long) em.createNamedQuery("M_USER.countRegisteredByUser_id", Long.class)
                    .setParameter("USER_ID", uid)
                    .getSingleResult();
            return Users_count;
        }

        /**
         * 画面から入力された従業員の登録内容を元にデータを1件作成し、従業員テーブルに登録する
         * @param ev 画面から入力された従業員の登録内容
         * @param pepper pepper文字列
         * @return バリデーションや登録処理中に発生したエラーのリスト
         */
        public List<String> create(UserView ev, String pepper) {

            //パスワードをハッシュ化して設定
            String pass = EncryptUtil.getPasswordEncrypt(ev.getPassword(), pepper);
            ev.setPassword(pass);

            //登録日時、更新日時は現在時刻を設定する
            LocalDateTime now = LocalDateTime.now();
            ev.setInp_date(now);
            ev.setUpd_date(now);

            create(ev);

            return null;
        }

        /**
         * 画面から入力された従業員の更新内容を元にデータを1件作成し、従業員テーブルを更新する
         * @param ev 画面から入力された従業員の登録内容
         * @param pepper pepper文字列
         * @return バリデーションや更新処理中に発生したエラーのリスト
         */
        public List<String> update(UserView ev, String pepper) {

            //idを条件に登録済みの従業員情報を取得する
            UserView savedUser = findOne(ev.getUser_id());

            boolean validateUser_id = false;
            if (!savedUser.getUser_id().equals(ev.getUser_id())) {
                //社員IDを更新する場合

                //社員IDについてのバリデーションを行う
                validateUser_id = true;
                //変更後の社員IDを設定する
                savedUser.setUser_id(ev.getUser_id());
            }

            boolean validatePass = false;
            if (ev.getPassword() != null && !ev.getPassword().equals("")) {
                //パスワードに入力がある場合

                //パスワードについてのバリデーションを行う
                validatePass = true;

                //変更後のパスワードをハッシュ化し設定する
                savedUser.setPassword(
                        EncryptUtil.getPasswordEncrypt(ev.getPassword(), pepper));
            }

            savedUser.setUser_name(ev.getUser_name()); //変更後の氏名を設定する
            savedUser.setUser_role(ev.getUser_role()); //変更後の権限を設定する

            //更新日時に現在時刻を設定する
            LocalDateTime today = LocalDateTime.now();
            savedUser.setUpd_date(today);


                update(savedUser);

                return null;
        }

        /**
         * idを条件に従業員データを論理削除する
         * @param id
         */
        public void destroy(Integer id) {

            //idを条件に登録済みの従業員情報を取得する
            UserView savedUser = findOne(id);

            //更新日時に現在時刻を設定する
            LocalDateTime today = LocalDateTime.now();
            savedUser.setUpd_date(today);

            //論理削除フラグをたてる
            savedUser.setDel_flg(JpaConst.FLG_TRUE);

            //更新処理を行う
            update(savedUser);

        }

        /**
         * 社員IDとパスワードを条件に検索し、データが取得できるかどうかで認証結果を返却する
         * @param User_id 社員ID
         * @param plainPass パスワード
         * @param pepper pepper文字列
         * @return 認証結果を返却す(成功:true 失敗:false)
         */
        public Boolean validateLogin(String User_id, String plainPass, String pepper) {

            boolean isValidUser = false;
            if (User_id != null && !User_id.equals("") && plainPass != null && !plainPass.equals("")) {
                UserView ev = findOne(User_id, plainPass, pepper);

                if (ev != null && ev.getUser_id() != null) {

                    //データが取得できた場合、認証成功
                    isValidUser = true;
                }
            }

            //認証結果を返却する
            return isValidUser;
        }

        /**
         * idを条件にデータを1件取得し、Userのインスタンスで返却する
         * @param id
         * @return 取得データのインスタンス
         */
        private User findOneInternal(int id) {
            User e = em.find(User.class, id);

            return e;
        }

        /**
         * 従業員データを1件登録する
         * @param ev 従業員データ
         * @return 登録結果(成功:true 失敗:false)
         */
        private void create(UserView ev) {

            em.getTransaction().begin();
            em.persist(UserConverter.toModel(ev));
            em.getTransaction().commit();

        }

        /**
         * 従業員データを更新する
         * @param ev 画面から入力された従業員の登録内容
         */
        private void update(UserView ev) {

            em.getTransaction().begin();
            User e = findOneInternal(ev.getUser_id());
            UserConverter.copyViewToModel(e, ev);
            em.getTransaction().commit();

        }

}
