package constants;

public interface JpaConst {

        //persistence-unit名
        String PERSISTENCE_UNIT_NAME = "daily_report_system";

        //データ取得件数の最大値
        int ROW_LIMIT = 15; //1ページに表示するレコードの数

        int ROLE_ADMIN = 1; //管理者権限ON(管理者)
        int ROLE_GENERAL = 0; //管理者権限OFF(一般)
        int FLG_TRUE = 1; //削除フラグON(削除済み)
        int FLG_FALSE = 0; //削除フラグOFF(現役)
        String PEPPER="qwerty";

        static enum adminOnlyMenu{
            m_user_index
        }
}
