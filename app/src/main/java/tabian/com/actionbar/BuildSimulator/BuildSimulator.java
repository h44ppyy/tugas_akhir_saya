package tabian.com.actionbar.BuildSimulator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import tabian.com.actionbar.Database.DatabaseOpenHelper;
import tabian.com.actionbar.Database.HeroTable;
import tabian.com.actionbar.Database.ItemTable;
import tabian.com.actionbar.R;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class BuildSimulator extends AppCompatActivity {
    HeroTable heroTable;
    ItemTable itemTable;
    private String selectedHeroName = "Earthshaker";
    private int level;
    private String selectedItemOneName = "Divine Rapier";
    private String selectedItemTwoName = "Divine Rapier";
    private String selectedItemThreeName = "Divine Rapier";
    private String selectedItemFourName = "Divine Rapier";
    private String selectedItemFiveName = "Divine Rapier";
    private String selectedItemSixName = "Divine Rapier";
    private ArrayList<String> selectedItemNames;
    private ArrayList<String> heroNames;
    private ArrayList<String> itemNames;
    private static final int baseHealth = 200;
    private static final int healthPerStrength = 20;
    private int baseMana = 50;
    private TextView strengthText;
    private TextView agilityText;
    private TextView intelligenceText;
    private TextView healthText;
    private TextView healthRegenerationText;
    private TextView manaText;
    private TextView manaRegenerationText;
    private TextView damageText;
    private TextView attackSpeedText;
    private TextView physicalArmorText;
    private TextView magicalArmorText;
    private ImageView simulatorHeroImage;
    private ImageView simulatorItemOneImage;
    private ImageView simulatorItemTwoImage;
    private ImageView simulatorItemThreeImage;
    private ImageView simulatorItemFourImage;
    private ImageView simulatorItemFiveImage;
    private ImageView simulatorItemSixImage;
    private static final String HERO_IMAGE_PATH = "file:///android_asset/hero_thumbnail/";
    private static final String HERO_IMAGE_FORMAT = ".png";
    private static final String ITEM_IMAGE_PATH = "file:///android_asset/item_thumbnail/";
    private static final String ITEM_IMAGE_FORMAT = ".png";
    private TextView movementSpeedText;
    private TextView DPSText;
    private TextView physicalEHPText;
    private TextView magicalEHPText;
    private TextView criticalStrikeChanceText;
    private TextView bashChanceText;

    private void displayStatistics() {
        displayStrength();
        displayAgility();
        displayIntelligence();
        displayHealth();
        displayHealthRegeneration();
        displayMana();
        displayManaRegeneration();
        displayDamage();
        displayAttackSpeed();
        displayPhysicalArmor();
        displayMagicalArmor();
        displayHeroImage();
        displayItemImage();
        displayMovementSpeed();
        displayDPS();
        displayPhysicalEHP();
        displayMagicalEHP();
        displayCriticalStrikeChance();
        displayBashChance();
    }

    private void displayItemImage() {
        Picasso.with(this).load(ITEM_IMAGE_PATH + selectedItemOneName + ITEM_IMAGE_FORMAT).into(simulatorItemOneImage);
        Picasso.with(this).load(ITEM_IMAGE_PATH + selectedItemTwoName + ITEM_IMAGE_FORMAT).into(simulatorItemTwoImage);
        Picasso.with(this).load(ITEM_IMAGE_PATH + selectedItemThreeName + ITEM_IMAGE_FORMAT).into(simulatorItemThreeImage);
        Picasso.with(this).load(ITEM_IMAGE_PATH + selectedItemFourName + ITEM_IMAGE_FORMAT).into(simulatorItemFourImage);
        Picasso.with(this).load(ITEM_IMAGE_PATH + selectedItemFiveName + ITEM_IMAGE_FORMAT).into(simulatorItemFiveImage);
        Picasso.with(this).load(ITEM_IMAGE_PATH + selectedItemSixName + ITEM_IMAGE_FORMAT).into(simulatorItemSixImage);
    }

    private void displayHeroImage() {
        Picasso.with(this).load(HERO_IMAGE_PATH + selectedHeroName + HERO_IMAGE_FORMAT).into(simulatorHeroImage);
    }

    private void displayBashChance() {
        float chance = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            chance += (100 - 100 * (1 - chance / 100) * (1 - itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_BASH_CHANCE, selectedItemNames.get(i)) / 100)) - chance;
        }

        bashChanceText.setText(String.valueOf((int) chance));
    }

    private void displayMagicalEHP() {
        float armor = Integer.parseInt(magicalArmorText.getText().toString());

        float hp = Integer.parseInt(healthText.getText().toString());

        float ehp = hp / (1 - armor / 100);

        magicalEHPText.setText(String.valueOf((int) ehp));
    }

    private void displayCriticalStrikeChance() {
        float chance = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            chance += (100 - 100 * (1 - chance / 100) * (1 - itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_CRITICAL_STRIKE_CHANCE, selectedItemNames.get(i)) / 100)) - chance;
        }

        criticalStrikeChanceText.setText(String.valueOf((int) chance));
    }

    private void displayPhysicalEHP() {
        float basePhysicalArmor = 0;

        basePhysicalArmor += heroTable.getHeroAttribute(HeroTable.COLUMN_BASE_PHYSICAL_ARMOR, selectedHeroName);

        basePhysicalArmor += Integer.parseInt(agilityText.getText().toString()) / 7;

        float bonusPhysicalArmor = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            bonusPhysicalArmor += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_PHYSICAL_ARMOR, selectedItemNames.get(i));
        }

        float armor = basePhysicalArmor + bonusPhysicalArmor;

        int hp = Integer.parseInt(healthText.getText().toString());

        int ehp = (int) (hp / (1 - 0.06 * armor / (1 + (0.06 * armor))));

        physicalEHPText.setText(String.valueOf(ehp));
    }

    private void displayDPS() {
        float baseDamage = 0;

        baseDamage += (heroTable.getHeroAttribute(HeroTable.COLUMN_BASE_MINIMUM_DAMAGE, selectedHeroName)
                + heroTable.getHeroAttribute(HeroTable.COLUMN_BASE_MAXIMUM_DAMAGE, selectedHeroName)) / 2;

        String primaryAttribute = heroTable.getHeroPrimaryAttribute(selectedHeroName);

        if (primaryAttribute.equals("strength")) {
            baseDamage += Integer.parseInt(strengthText.getText().toString());
        }
        if (primaryAttribute.equals("agility")) {
            baseDamage += Integer.parseInt(agilityText.getText().toString());
        }
        if (primaryAttribute.equals("intelligence")) {
            baseDamage += Integer.parseInt(intelligenceText.getText().toString());
        }

        for (int i = 0; i < selectedItemNames.size(); i++) {
            baseDamage += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_BASE_DAMAGE_CONSTANT, selectedItemNames.get(i));
        }

        float bonusDamage = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            bonusDamage += baseDamage * itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_DAMAGE_PERCENTAGE, selectedItemNames.get(i)) / 100;
        }

        for (int i = 0; i < selectedItemNames.size(); i++) {
            bonusDamage += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_DAMAGE_CONSTANT, selectedItemNames.get(i));
        }

        float damage = baseDamage + bonusDamage;

        double attackPerSecond = (1 / 1.7) * (Integer.parseInt(attackSpeedText.getText().toString()) / 100);

        double dps = damage * attackPerSecond;

        DPSText.setText(String.valueOf((int) dps));
    }

    private void displayMovementSpeed() {
        float movementSpeed = 0;

        movementSpeed += heroTable.getHeroAttribute(HeroTable.COLUMN_BASE_MOVEMENT_SPEED, selectedHeroName);

        for (int i = 0; i < selectedItemNames.size(); i++) {
            movementSpeed += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_MOVEMENT_SPEED_CONSTANT, selectedItemNames.get(i));
        }

        int percent = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            percent += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_MOVEMENT_SPEED_PERCENTAGE, selectedItemNames.get(i));
        }

        movementSpeed += movementSpeed * percent / 100;

        movementSpeedText.setText(String.valueOf((int) movementSpeed));
    }

    private void displayStrength() {
        float strength = 0;

        strength += heroTable.getHeroAttribute(HeroTable.COLUMN_BASE_STRENGTH, selectedHeroName)
                + heroTable.getHeroAttribute(HeroTable.COLUMN_STRENGTH_GAIN, selectedHeroName) * (level - 1);

        for (int i = 0; i < selectedItemNames.size(); i++) {
            strength += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_ALL_ATTRIBUTES, selectedItemNames.get(i))
                    + itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_STRENGTH, selectedItemNames.get(i));
        }

        strengthText.setText(String.valueOf((int) strength));
    }

    private void displayAgility() {
        float agility = 0;

        agility += heroTable.getHeroAttribute(HeroTable.COLUMN_BASE_AGILITY, selectedHeroName)
                + heroTable.getHeroAttribute(HeroTable.COLUMN_AGILITY_GAIN, selectedHeroName) * (level - 1);

        for (int i = 0; i < selectedItemNames.size(); i++) {
            agility += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_ALL_ATTRIBUTES, selectedItemNames.get(i))
                    + itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_AGILITY, selectedItemNames.get(i));
        }

        agilityText.setText(String.valueOf((int) agility));
    }

    private void displayIntelligence() {
        float intelligence = 0;

        intelligence += heroTable.getHeroAttribute(HeroTable.COLUMN_BASE_INTELLIGENCE, selectedHeroName)
                + heroTable.getHeroAttribute(HeroTable.COLUMN_INTELLIGENCE_GAIN, selectedHeroName) * (level - 1);

        for (int i = 0; i < selectedItemNames.size(); i++) {
            intelligence += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_ALL_ATTRIBUTES, selectedItemNames.get(i))
                    + itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_INTELLIGENCE, selectedItemNames.get(i));
        }

        intelligenceText.setText(String.valueOf((int) intelligence));
    }

    private void displayHealth() {
        float health = baseHealth;

        health += Integer.parseInt(strengthText.getText().toString()) * healthPerStrength;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            health += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_HEALTH_CONSTANT, selectedItemNames.get(i));
        }

        float percent = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            percent += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_HEALTH_PERCENTAGE, selectedItemNames.get(i));
        }

        health += health * percent / 100;

        healthText.setText(String.valueOf((int) health));
    }

    private void displayHealthRegeneration() {
        float healthRegeneration = 0;

        healthRegeneration += Integer.parseInt(strengthText.getText().toString()) * 0.03;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            healthRegeneration += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_HEALTH_REGENERATION_CONSTANT, selectedItemNames.get(i));
        }

        float percent = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            percent += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_HEALTH_REGENERATION_PERCENTAGE, selectedItemNames.get(i));
        }

        healthRegeneration += Integer.parseInt(healthText.getText().toString()) * percent / 100;

        healthRegenerationText.setText(String.valueOf((int) healthRegeneration));
    }

    private void displayMana() {
        float mana = baseMana;

        mana += Integer.parseInt(intelligenceText.getText().toString()) * 12;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            mana += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_MANA_CONSTANT, selectedItemNames.get(i));
        }

        float percent = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            percent += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_MANA_PERCENTAGE, selectedItemNames.get(i));
        }

        mana += mana * percent / 100;

        manaText.setText(String.valueOf((int) mana));
    }

    private void displayManaRegeneration() {
        float manaRegeneration = 0;

        manaRegeneration += Integer.parseInt(intelligenceText.getText().toString()) * 0.04;

        float percent = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            percent += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_MANA_REGENERATION_PERCENTAGE, selectedItemNames.get(i));
        }

        manaRegeneration += manaRegeneration * percent / 100;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            manaRegeneration += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_MANA_REGENERATION_CONSTANT, selectedItemNames.get(i));
        }

        manaRegenerationText.setText(String.valueOf((int) manaRegeneration));
    }

    private void displayDamage() {
        float baseDamage = 0;

        baseDamage += (heroTable.getHeroAttribute(HeroTable.COLUMN_BASE_MINIMUM_DAMAGE, selectedHeroName)
                + heroTable.getHeroAttribute(HeroTable.COLUMN_BASE_MAXIMUM_DAMAGE, selectedHeroName)) / 2;

        String primaryAttribute = heroTable.getHeroPrimaryAttribute(selectedHeroName);

        if (primaryAttribute.equals("strength")) {
            baseDamage += Integer.parseInt(strengthText.getText().toString());
        }
        if (primaryAttribute.equals("agility")) {
            baseDamage += Integer.parseInt(agilityText.getText().toString());
        }
        if (primaryAttribute.equals("intelligence")) {
            baseDamage += Integer.parseInt(intelligenceText.getText().toString());
        }

        for (int i = 0; i < selectedItemNames.size(); i++) {
            baseDamage += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_BASE_DAMAGE_CONSTANT, selectedItemNames.get(i));
        }

        float bonusDamage = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            bonusDamage += baseDamage * itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_DAMAGE_PERCENTAGE, selectedItemNames.get(i)) / 100;
        }

        for (int i = 0; i < selectedItemNames.size(); i++) {
            bonusDamage += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_DAMAGE_CONSTANT, selectedItemNames.get(i));
        }

        damageText.setText(String.valueOf((int) baseDamage) + " + " + String.valueOf((int) bonusDamage));
    }

    private void displayAttackSpeed() {
        float attackSpeed = 100;

        attackSpeed += Integer.parseInt(agilityText.getText().toString());

        for (int i = 0; i < selectedItemNames.size(); i++) {
            attackSpeed += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_ATTACK_SPEED, selectedItemNames.get(i));
        }

        attackSpeedText.setText(String.valueOf((int) attackSpeed));
    }

    private void displayPhysicalArmor() {
        float basePhysicalArmor = 0;

        basePhysicalArmor += heroTable.getHeroAttribute(HeroTable.COLUMN_BASE_PHYSICAL_ARMOR, selectedHeroName);

        basePhysicalArmor += Integer.parseInt(agilityText.getText().toString()) / 7;

        float bonusPhysicalArmor = 0;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            bonusPhysicalArmor += itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_PHYSICAL_ARMOR, selectedItemNames.get(i));
        }

        physicalArmorText.setText(String.valueOf((int) basePhysicalArmor) + " + " + String.valueOf((int) bonusPhysicalArmor));
    }

    private void displayMagicalArmor() {
        float magicalArmor = 25;

        for (int i = 0; i < selectedItemNames.size(); i++) {
            magicalArmor += (100 - 100 * (1 - magicalArmor / 100) * (1 - itemTable.getItemAttribute(ItemTable.COLUMN_BONUS_MAGICAL_ARMOR, selectedItemNames.get(i)) / 100)) - magicalArmor;
        }

        magicalArmorText.setText(String.valueOf((int) magicalArmor));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_build_simulator);
        heroTable = new HeroTable();itemTable = new ItemTable();
        level = 1;
        setHeroSpinner();
        setLevelSpinner();
        setItemSpinners();
        selectedItemNames = new ArrayList<>();
        selectedItemNames.add(selectedItemOneName);
        selectedItemNames.add(selectedItemTwoName);
        selectedItemNames.add(selectedItemThreeName);
        selectedItemNames.add(selectedItemFourName);
        selectedItemNames.add(selectedItemFiveName);
        selectedItemNames.add(selectedItemSixName);
        strengthText = (TextView) findViewById(R.id.simulatorStrengthText);
        agilityText = (TextView) findViewById(R.id.simulatorAgilityText);
        intelligenceText = (TextView) findViewById(R.id.simulatorIntelligenceText);
        healthText = (TextView) findViewById(R.id.simulatorHealthText);
        healthRegenerationText = (TextView) findViewById(R.id.simulatorHealthRegenerationText);
        manaText = (TextView) findViewById(R.id.simulatorManaText);
        manaRegenerationText = (TextView) findViewById(R.id.simulatorManaRegenerationText);
        damageText = (TextView) findViewById(R.id.simulatorDamageText);
        attackSpeedText = (TextView) findViewById(R.id.simulatorAttackSpeedText);
        physicalArmorText = (TextView) findViewById(R.id.simulatorphysicalArmorText);
        magicalArmorText = (TextView) findViewById(R.id.simulatorMagicalArmorText);
        simulatorHeroImage = (ImageView) findViewById(R.id.simulatorHeroImage);
        simulatorItemOneImage = (ImageView) findViewById(R.id.simulatorItemOneImage);
        simulatorItemTwoImage = (ImageView) findViewById(R.id.simulatorItemTwoImage);
        simulatorItemThreeImage = (ImageView) findViewById(R.id.simulatorItemThreeImage);
        simulatorItemFourImage = (ImageView) findViewById(R.id.simulatorItemFourImage);
        simulatorItemFiveImage = (ImageView) findViewById(R.id.simulatorItemFiveImage);
        simulatorItemSixImage = (ImageView) findViewById(R.id.simulatorItemSixImage);
        movementSpeedText = (TextView) findViewById(R.id.simulatorMovementSpeedText);
        DPSText = (TextView) findViewById(R.id.simulatorDPSText);
        physicalEHPText = (TextView) findViewById(R.id.simulatorPhysicalEHPText);
        magicalEHPText = (TextView) findViewById(R.id.simulatorMagicalEHPText);
        criticalStrikeChanceText = (TextView) findViewById(R.id.simulatorCriticalStrikeChanceText);
        bashChanceText = (TextView) findViewById(R.id.simulatorBashChanceText);
        displayStatistics();
    }

    private void setHeroSpinner() {
        SQLiteDatabase database = openOrCreateDatabase(DatabaseOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);

        String query = "SELECT " + " hero_name FROM Hero";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        heroNames = new ArrayList<>();

        while(!cursor.isAfterLast()) {
            String heroName = cursor.getString(cursor.getColumnIndex(HeroTable.COLUMN_HERO_NAME));

            heroNames.add(heroName);

            cursor.moveToNext();
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_dropdown_item,
                heroNames);

        final Spinner spinner = (Spinner)findViewById(R.id.heroSpinner);

        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedHeroName = spinner.getSelectedItem().toString();
                displayStatistics();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cursor.close();

        database.close();
    }

    private void setLevelSpinner() {
        final ArrayList<String> levels = new ArrayList<>();

        for (int i = 1; i < 26; i++) {
            levels.add(String.valueOf(i));
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_dropdown_item,
                levels);

        final Spinner spinner = (Spinner)findViewById(R.id.levelSpinner);

        spinner.setAdapter(spinnerArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                level = Integer.parseInt(spinner.getSelectedItem().toString());
                displayStatistics();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void setItemSpinners() {
        SQLiteDatabase database = openOrCreateDatabase(DatabaseOpenHelper.DATABASE_NAME, MODE_PRIVATE, null);

        String query = "SELECT item_name " + "FROM Item";

        Cursor cursor = database.rawQuery(query, null);

        cursor.moveToFirst();

        itemNames = new ArrayList<>();

        while(!cursor.isAfterLast()) {
            String itemName = cursor.getString(cursor.getColumnIndex(ItemTable.COLUMN_ITEM_NAME));

            itemNames.add(itemName);

            cursor.moveToNext();
        }

        ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<>(this,
                R.layout.spinner_dropdown_item,
                itemNames);

        final Spinner itemOneSpinner = (Spinner)findViewById(R.id.itemOneSpinner);

        itemOneSpinner.setAdapter(spinnerArrayAdapter);

        itemOneSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemOneName = itemOneSpinner.getSelectedItem().toString();
                selectedItemNames.set(0, selectedItemOneName);
                displayStatistics();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner itemTwoSpinner = (Spinner)findViewById(R.id.itemTwoSpinner);

        itemTwoSpinner.setAdapter(spinnerArrayAdapter);

        itemTwoSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemTwoName = itemTwoSpinner.getSelectedItem().toString();
                selectedItemNames.set(1, selectedItemTwoName);
                displayStatistics();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner itemThreeSpinner = (Spinner)findViewById(R.id.itemThreeSpinner);

        itemThreeSpinner.setAdapter(spinnerArrayAdapter);

        itemThreeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemThreeName = itemThreeSpinner.getSelectedItem().toString();
                selectedItemNames.set(2, selectedItemThreeName);
                displayStatistics();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner itemFourSpinner = (Spinner)findViewById(R.id.itemFourSpinner);

        itemFourSpinner.setAdapter(spinnerArrayAdapter);

        itemFourSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemFourName = itemFourSpinner.getSelectedItem().toString();
                selectedItemNames.set(3, selectedItemFourName);
                displayStatistics();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner itemFiveSpinner = (Spinner)findViewById(R.id.itemFiveSpinner);

        itemFiveSpinner.setAdapter(spinnerArrayAdapter);

        itemFiveSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemFiveName = itemFiveSpinner.getSelectedItem().toString();
                selectedItemNames.set(4, selectedItemFiveName);
                displayStatistics();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        final Spinner itemSixSpinner = (Spinner)findViewById(R.id.itemSixSpinner);

        itemSixSpinner.setAdapter(spinnerArrayAdapter);

        itemSixSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                selectedItemSixName = itemSixSpinner.getSelectedItem().toString();
                selectedItemNames.set(5, selectedItemSixName);
                displayStatistics();
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        cursor.close();

        database.close();
    }
}