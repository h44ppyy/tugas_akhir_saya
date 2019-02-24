package tabian.com.actionbar.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class HeroTable {

    public static final String TABLE_NAME = "Hero";

    public static final String COLUMN_HERO_NAME = "hero_name";

    public static final String COLUMN_PRIMARY_ATTRIBUTE = "primary_attribute";

    public static final String COLUMN_BASE_STRENGTH = "base_strength";

    public static final String COLUMN_STRENGTH_GAIN = "strength_gain";

    public static final String COLUMN_BASE_AGILITY = "base_agility";

    public static final String COLUMN_AGILITY_GAIN = "agility_gain";

    public static final String COLUMN_BASE_INTELLIGENCE = "base_intelligence";

    public static final String COLUMN_INTELLIGENCE_GAIN = "intelligence_gain";

    public static final String COLUMN_BASE_MINIMUM_DAMAGE = "base_minimum_damage";

    public static final String COLUMN_BASE_MAXIMUM_DAMAGE = "base_maximum_damage";

    public static final String COLUMN_BASE_PHYSICAL_ARMOR = "base_physical_armor";

    public static final String COLUMN_BASE_MOVEMENT_SPEED = "base_movement_speed";

    public static final String COLUMN_ABILITY_ONE_ID = "ability_1_id";

    public static final String COLUMN_ABILITY_TWO_ID = "ability_2_id";

    public static final String COLUMN_ABILITY_THREE_ID = "ability_3_id";

    public static final String COLUMN_ABILITY_FOUR_ID = "ability_4_id";

    public float getHeroAttribute(String columnName, String heroName) {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(DatabaseOpenHelper.DATABASE_PATH + DatabaseOpenHelper.DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);

        String query = "SELECT " + columnName +
                " FROM " + HeroTable.TABLE_NAME +
                " WHERE " + HeroTable.COLUMN_HERO_NAME + " = " + "\"" + heroName + "\"";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        float result = cursor.getFloat(cursor.getColumnIndex(columnName));

        cursor.close();

        database.close();

        return result;
    }

    public String getHeroPrimaryAttribute(String heroName) {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(DatabaseOpenHelper.DATABASE_PATH + DatabaseOpenHelper.DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);

        String query = "SELECT " + COLUMN_PRIMARY_ATTRIBUTE +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_HERO_NAME + " = " + "\"" + heroName + "\"";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        String result = cursor.getString(cursor.getColumnIndex(COLUMN_PRIMARY_ATTRIBUTE));

        cursor.close();

        database.close();

        return result;
    }
}
