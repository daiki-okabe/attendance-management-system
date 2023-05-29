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

        //管理者のみのメニュー一覧
        static enum adminOnlyMenu{
            m_user_index//ユーザーマスタ
        }

        //勤務状況
        static enum work_class{
            work,//出勤
            rest,//有給
            absence,//欠勤
            suspension//休職
        }

        enum DayOfWeek  {
            MONDAY,
            TUESDAY,
            WEDNESDAY,
           THURSDAY,
            FRIDAY,
            SATURDAY,
            SUNDAY;
        }

        final int DAY=24;
        final int HOUR=60;
        final int MINUTE=60;
        final int SECONDS=60;

        final int DEFAULT_WORK_HOUR_STATS=160;
        final int DEFAULT_WORK_HOUR=8;
        final int DEFAULT_WORK_REST=1;

        final String NEXT=">";
        final String UNDO="<";
        final String INPUT="登録";
        final String UPDATE="更新";
        final String DELETE="削除";
}
