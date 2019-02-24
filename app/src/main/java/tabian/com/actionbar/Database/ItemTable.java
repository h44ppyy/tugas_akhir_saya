package tabian.com.actionbar.Database;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class ItemTable {
    public static final String TABLE_NAME = "Item";
    public static final String COLUMN_ITEM_NAME = "item_name";
    public static final String COLUMN_BONUS_HEALTH_CONSTANT = "bonus_health_constant";
    public static final String COLUMN_BONUS_HEALTH_PERCENTAGE = "bonus_health_percentage";
    public static final String COLUMN_BONUS_HEALTH_REGENERATION_CONSTANT = "bonus_health_regeneration_constant";
    public static final String COLUMN_BONUS_HEALTH_REGENERATION_PERCENTAGE = "bonus_health_regeneration_percentage";
    public static final String COLUMN_BONUS_MANA_CONSTANT = "bonus_mana_constant";
    public static final String COLUMN_BONUS_MANA_PERCENTAGE = "bonus_mana_percentage";
    public static final String COLUMN_BONUS_MANA_REGENERATION_CONSTANT = "bonus_mana_regeneration_constant";
    public static final String COLUMN_BONUS_MANA_REGENERATION_PERCENTAGE = "bonus_mana_regeneration_percentage";
    public static final String COLUMN_BONUS_ALL_ATTRIBUTES = "bonus_all_attributes";
    public static final String COLUMN_BONUS_STRENGTH = "bonus_strength";
    public static final String COLUMN_BONUS_AGILITY = "bonus_agility";
    public static final String COLUMN_BONUS_INTELLIGENCE = "bonus_intelligence";
    public static final String COLUMN_BONUS_BASE_DAMAGE_CONSTANT = "bonus_base_damage_constant";
    public static final String COLUMN_BONUS_DAMAGE_CONSTANT = "bonus_damage_constant";
    public static final String COLUMN_BONUS_DAMAGE_PERCENTAGE = "bonus_damage_percentage";
    public static final String COLUMN_BONUS_ATTACK_SPEED = "bonus_attack_speed";
    public static final String COLUMN_BONUS_ATTACK_RANGE = "bonus_attack_range";
    public static final String COLUMN_BONUS_PHYSICAL_ARMOR = "bonus_physical_armor";
    public static final String COLUMN_BONUS_MAGICAL_ARMOR = "bonus_magical_armor";
    public static final String COLUMN_BONUS_MOVEMENT_SPEED_CONSTANT = "bonus_movement_speed_constant";
    public static final String COLUMN_BONUS_MOVEMENT_SPEED_PERCENTAGE = "bonus_movement_speed_percentage";
    public static final String COLUMN_BONUS_EVASION = "bonus_evasion";
    public static final String COLUMN_BONUS_CRITICAL_STRIKE_CHANCE = "bonus_critical_strike_chance";
    public static final String COLUMN_BONUS_BASH_CHANCE = "bonus_bash_chance";

    public float getItemAttribute(String columnName, String itemName) {
        SQLiteDatabase database = SQLiteDatabase.openDatabase(DatabaseOpenHelper.DATABASE_PATH + DatabaseOpenHelper.DATABASE_NAME, null, SQLiteDatabase.OPEN_READONLY);

        String query = "SELECT " + columnName +
                " FROM " + TABLE_NAME +
                " WHERE " + COLUMN_ITEM_NAME + " = " + "\"" + itemName + "\"";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        float result = cursor.getFloat(cursor.getColumnIndex(columnName));

        cursor.close();

        database.close();

        return result;
    }
}