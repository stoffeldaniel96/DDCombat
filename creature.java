package creature;

import java.util.ArrayList;
import pathfinding.*;

public class creature implements Mover{
        private String name;
        private float challengeRating;
        private String[] vulnerabilities;
        private String[] resistances;
        private String[] immunities;
        private int type;
        private String[] size;
        private int[] attributes;
        private int proficienyBonus;
        private boolean[] savingThrow;
        private boolean[] skill;
        private Language[] languages;
        private String proficiencies;
        private int armorClass;
        private int speed;
        private int hitPoints;
        private String[] attacks;
        private int[] attackBonus;
        private Dice[] attackDamage;
        private int passivePerception;
        private ArrayList<String> equipment;
        private int cIter = 0;
        
        public creature() {
                name = "Creature" + (cIter += 1);
                /*
                attributes = rollAttributes();
                speed = initSpeed();
                savingThrowProfs = initSavingThrowProfs();
                skillProfs = getRandomSkillProfsSubset();
                */
                hitPoints = initHitPoints();
                //languages = initLanguages();
                equipment = new ArrayList<>();

        }

/*
        public int[] rollAtributes() {
                switch (rollMode) {
                        case AVERAGE:
                                return new int[]{8, 10, 12, 13, 14, 15};
                        case ROLL: //will be different
                                return new int[]{8, 10, 12, 13, 14, 15};
                        default:
                                throw new IllegalArgumentException();
                }
        }
*/
        public int initHitPoints() {
        	return 10;
        }

        public ArrayList<Language> initLanguage() {
                ArrayList<Language> languages = new ArrayList<>();
                languages.add(Language.COMMON);
				return languages;
        }

        public String getName() {
                return name;
        }
        
        public void setName() {
        	this.name = name;
        }

        public int[] getAttributes() {
                return attributes;
        }
        
        public void setAttributes() {
        	this.attributes = attributes;
        }

        public int getAttributes(int i) {
                return attributes[i];
        }
        

        public int[] getAttributeModifiers() {
                int[] attributeModifiers = new int[attributes.length];
                for (int i = 0; i < attributes.length; i++) {
                        attributeModifiers[i] = (int) Math.floor((((double) attributes[i]) - 10) / 2);
                }
                return attributeModifiers;

        }
        
        public void setAttributeModifiers() {
        	this.attributeModifiers = attributeModifiers;
        }

        public String getAttributeModifierString(int i) {
                int attributeModifier = (int) Math.floor((((double) attributes[i]) - 10) / 2);
                String attributeModifierString = attributeModifier > 0 ? "+" + attributeModifier : Integer.toString(attributeModifier);
                return attributeModifierString;
        }
        
        public void setAttributeModifierString() {
        	this.attributeModifierString = attributeModifierString;
        }

        public int getProficienyBonus() {
                return proficienyBonus;
        }
        
        public void setProficiencyBonus() {
        	this.proficienyBonus = proficiencyBonus;
        }

        public boolean[] getSavingThrow() {
                return savingThrow;
        }
        
        public void setSavingThrow() {
        	this.savingThrow = savingThrow;
        }

        public boolean[] getSkills() {
                return skill;
        }
        
        public void setSkills() {
        	this.skill = skill;
        }

        public Language[] getLanguages() {
                return languages;
        }
        
        public void setLanguages() {
        	this.languages = languges;
        }

        public int getAc() {
                return ac;
        }
        
        public void setAc() {
        	this.ac = ac;
        }

        public int getSpeed() {
                return speed;
        }
        
        public void setSpeed() {
        	this.speed = speed;
        }

        public int getHP() {
                return hp = hp;
        }
        
        public void setHP() {
        	this.hp = hp;
        }

        public String[] getAttacks() {
                return attacks;
        }
        
        public void setAttacks() {
        	this.attacks = attacks;
        }

        public int[] getAttackBonus() {
                return attackBonus;
        }
        
        public void setAttackBonus() {
        	this.attackBonus = attackBonus;
        }

        public Dice[] getAttackDamage() {
                return attackDamage;
        }
        
        public void setAttackDamage() {
        	this.attackDamage = attackDamage;
        }

        public ArrayList<String> getEquipment() {
                return equipment;
        }
        
        public void setEquipment() {
        	this.equipment = equipment;
        }

        public String getEquipmentFormatted() {
                String equipmentText = "";
                for (int i = 0; i < equipment.size(); i++) {
                        if (i < equipment.size() - 1) {
                                equipmentText += equipment.get(i) + "\n";
                        } else {
                                equipmentText += equipment.get(i);
                        }
                }
                return equipmentText;
        }

        public String[] getVulnerabilities() {
                return vulnerabilities;
        }
        
        public void setVulnerabilities() {
        	this.vulnerabilities = vulnerabilities;
        }

        public String[] getResistances() {
                return resistances;
        }
        
        public void setResistances( {
        	this.resistances = resistances;
        }

        public String[] getImmunities() {
                return immunities;
        }
        
        public void setImmunities() {
        	this.immunities = immunities;
        }

        public String[] getSize() {
                return size;
        }
        
        public void setSize() {
        	this.size = size;
        }
  
    	
    	/**
    	 * Create a new mover to be used while path finder
    	 * 
    	 * @param type The ID of the unit moving
    	 */
    	public creature(int type) {
    		this.type = type;
    	}
    	
    	/**
    	 * Get the ID of the unit moving
    	 * 
    	 * @return The ID of the unit moving
    	 */
    	public int getType() {
    		return type;
    	}
    	
    	public void setType() {
    		this.type = type;
    	}
}
