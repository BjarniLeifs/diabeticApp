package ru.calendar;

import com.google.gson.annotations.SerializedName;

public class Food {
    @SerializedName("_id")
    private String id;
    @SerializedName("Nafn")
    private String nameIce;
    @SerializedName("Name")
    private String nameEng;
    @SerializedName("Fæðuflokkur")
    private String foodCategory;
    @SerializedName("Aðalfl")
    private int mainCategory;
    @SerializedName("Undirfl")
    private int subCategory;
    @SerializedName("Ætur hluti")
    private double edible;
    @SerializedName("Prótein")
    private double proteins;
    @SerializedName("Fita")
    private double fat;
    @SerializedName("Mettaðar fitusýrur")
    private double saturatedFat;
    @SerializedName("cis-Einómettaðar fitusýrur")
    private double cisMonounsaturatedFattyAcids;
    @SerializedName("cis-Fjölómettaðar fitusýrur")
    private double cisPolyunsaturatedFattyAcids;
    @SerializedName("cis-Fjölómettaðar fitus. n-3 langar")
    private double cisPolyunsaturatedFattyAcidsNeg3Long;
    @SerializedName("trans-Fitusýrur")
    private double transFattyAcids;
    @SerializedName("Kólesteról")
    private double cholesterol;
    @SerializedName("Kolvetni, alls")
    private double totalCarbohydrates;
    @SerializedName("Sykrur")
    private double sugars;
    @SerializedName("Viðbættur sykur")
    private double addedSugar;
    @SerializedName("Trefjaefni")
    private double fiber;
    @SerializedName("Alkóhól")
    private double alcohol;
    @SerializedName("Steinefni, alls")
    private double totalMinerals;
    @SerializedName("Vatn")
    private double water;
    @SerializedName("A-vítamín, RJ")
    private double vitaminA;
    @SerializedName("Retinol")
    private double retinol;
    @SerializedName("Beta-karótín")
    private double betaCarotene;
    @SerializedName("D-vítamín")
    private double vitaminD;
    @SerializedName("E-vítamín, alfa-TJ")
    private double vitaminE;
    @SerializedName("B1-vítamín")
    private double vitaminB1;
    @SerializedName("B2-vítamín")
    private double vitaminB2;
    @SerializedName("Níasín-jafngildi")
    private double niacinEquivalents;
    @SerializedName("Níasín")
    private double niacin;
    @SerializedName("B6-vítamín")
    private double vitaminB6;
    @SerializedName("Fólat, alls")
    private double totalFolate;
    @SerializedName("B-12 vítamín")
    private double vitaminB12;
    @SerializedName("C-vítamín")
    private double vitaminC;
    @SerializedName("Kalk")
    private double calcium;
    @SerializedName("Fosfór")
    private double phosphorus;
    @SerializedName("Magnesíum")
    private double magnesium;
    @SerializedName("Natríum")
    private double sodium;
    @SerializedName("Kalíum")
    private double potassium;
    @SerializedName("Járn")
    private double iron;
    @SerializedName("Sink")
    private double sink;
    @SerializedName("Kopar")
    private double copper;
    @SerializedName("Joð")
    private double iodine;
    @SerializedName("Selen")
    private double selenium;
    @SerializedName("cis-Fjölómettaðar fitus. n-6")
    private double cisPolyunsaturatedFattyAcidsNeg6;
    @SerializedName("cis-Fjölómettaðar fitus. n-3")
    private double cisPolyunsaturatedFattyAcidsNeg3;

    //Getters
    public String getId() {
        return id;
    }
    public String getNameIce() {
        return nameIce;
    }
    public String getNameEng() {
        return nameEng;
    }
    public String getFoodCategory() {
        return foodCategory;
    }
    public int getMainCategory() {
        return mainCategory;
    }
    public int getSubCategory() {
        return subCategory;
    }
    public double getEdible() {
        return edible;
    }
    public double getProteins() {
        return proteins;
    }
    public double getFat() {
        return fat;
    }
    public double getSaturatedFat() {
        return saturatedFat;
    }
    public double getCisMonounsaturatedFattyAcids() {
        return cisMonounsaturatedFattyAcids;
    }
    public double getCisPolyunsaturatedFattyAcids() {
        return cisPolyunsaturatedFattyAcids;
    }
    public double getCisPolyunsaturatedFattyAcidsNeg3Long() {
        return cisPolyunsaturatedFattyAcidsNeg3Long;
    }
    public double getTransFattyAcids() {
        return transFattyAcids;
    }
    public double getCholesterol() {
        return cholesterol;
    }
    public double getTotalCarbohydrates() {
        return totalCarbohydrates;
    }
    public double getSugars() {
        return sugars;
    }
    public double getAddedSugar() {
        return addedSugar;
    }
    public double getFiber() {
        return fiber;
    }
    public double getAlcohol() {
        return alcohol;
    }
    public double getTotalMinerals() {
        return totalMinerals;
    }
    public double getWater() {
        return water;
    }
    public double getVitaminA() {
        return vitaminA;
    }
    public double getRetinol() {
        return retinol;
    }
    public double getBetaCarotene() {
        return betaCarotene;
    }
    public double getVitaminD() {
        return vitaminD;
    }
    public double getVitaminE() {
        return vitaminE;
    }
    public double getVitaminB1() {
        return vitaminB1;
    }
    public double getVitaminB2() {
        return vitaminB2;
    }
    public double getNiacinEquivalents() {
        return niacinEquivalents;
    }
    public double getNiacin() {
        return niacin;
    }
    public double getVitaminB6() {
        return vitaminB6;
    }
    public double getTotalFolate() {
        return totalFolate;
    }
    public double getVitaminB12() {
        return vitaminB12;
    }
    public double getVitaminC() {
        return vitaminC;
    }
    public double getCalcium() {
        return calcium;
    }
    public double getPhosphorus() {
        return phosphorus;
    }
    public double getMagnesium() {
        return magnesium;
    }
    public double getSodium() {
        return sodium;
    }
    public double getPotassium() {
        return potassium;
    }
    public double getIron() {
        return iron;
    }
    public double getSink() {
        return sink;
    }
    public double getCopper() {
        return copper;
    }
    public double getIodine() {
        return iodine;
    }
    public double getSelenium() {
        return selenium;
    }
    public double getCisPolyunsaturatedFattyAcidsNeg6() {
        return cisPolyunsaturatedFattyAcidsNeg6;
    }
    public double getCisPolyunsaturatedFattyAcidsNeg3() {
        return cisPolyunsaturatedFattyAcidsNeg3;
    }

    //Setters
    public void setId(String id) {
        this.id = id;
    }
    public void setNameIce(String nameIce) {
        this.nameIce = nameIce;
    }
    public void setNameEng(String nameEng) {
        this.nameEng = nameEng;
    }
    public void setFoodCategory(String foodCategory) {
        this.foodCategory = foodCategory;
    }
    public void setMainCategory(int mainCategory) {
        this.mainCategory = mainCategory;
    }
    public void setSubCategory(int subCategory) {
        this.subCategory = subCategory;
    }
    public void setEdible(double edible) {
        this.edible = edible;
    }
    public void setProteins(double proteins) {
        this.proteins = proteins;
    }
    public void setFat(double fat) {
        this.fat = fat;
    }
    public void setSaturatedFat(double saturatedFat) {
        this.saturatedFat = saturatedFat;
    }
    public void setCisMonounsaturatedFattyAcids(double cisMonounsaturatedFattyAcids) {
        this.cisMonounsaturatedFattyAcids = cisMonounsaturatedFattyAcids;
    }
    public void setCisPolyunsaturatedFattyAcids(double cisPolyunsaturatedFattyAcids) {
        this.cisPolyunsaturatedFattyAcids = cisPolyunsaturatedFattyAcids;
    }
    public void setCisPolyunsaturatedFattyAcidsNeg3Long(double cisPolyunsaturatedFattyAcidsNeg3Long) {
        this.cisPolyunsaturatedFattyAcidsNeg3Long = cisPolyunsaturatedFattyAcidsNeg3Long;
    }
    public void setTransFattyAcids(double transFattyAcids) {
        this.transFattyAcids = transFattyAcids;
    }
    public void setCholesterol(double cholesterol) {
        this.cholesterol = cholesterol;
    }
    public void setTotalCarbohydrates(double totalCarbohydrates) {
        this.totalCarbohydrates = totalCarbohydrates;
    }
    public void setSugars(double sugars) {
        this.sugars = sugars;
    }
    public void setAddedSugar(double addedSugar) {
        this.addedSugar = addedSugar;
    }
    public void setFiber(double fiber) {
        this.fiber = fiber;
    }
    public void setAlcohol(double alcohol) {
        this.alcohol = alcohol;
    }
    public void setTotalMinerals(double totalMinerals) {
        this.totalMinerals = totalMinerals;
    }
    public void setWater(double water) {
        this.water = water;
    }
    public void setVitaminA(double vitaminA) {
        this.vitaminA = vitaminA;
    }
    public void setRetinol(double retinol) {
        this.retinol = retinol;
    }
    public void setBetaCarotene(double betaCarotene) {
        this.betaCarotene = betaCarotene;
    }
    public void setVitaminD(double vitaminD) {
        this.vitaminD = vitaminD;
    }
    public void setVitaminE(double vitaminE) {
        this.vitaminE = vitaminE;
    }
    public void setVitaminB1(double vitaminB1) {
        this.vitaminB1 = vitaminB1;
    }
    public void setVitaminB2(double vitaminB2) {
        this.vitaminB2 = vitaminB2;
    }
    public void setNiacinEquivalents(double niacinEquivalents) {
        this.niacinEquivalents = niacinEquivalents;
    }
    public void setNiacin(double niacin) {
        this.niacin = niacin;
    }
    public void setVitaminB6(double vitaminB6) {
        this.vitaminB6 = vitaminB6;
    }
    public void setTotalFolate(double totalFolate) {
        this.totalFolate = totalFolate;
    }
    public void setVitaminB12(double vitaminB12) {
        this.vitaminB12 = vitaminB12;
    }
    public void setVitaminC(double vitaminC) {
        this.vitaminC = vitaminC;
    }
    public void setCalcium(double calcium) {
        this.calcium = calcium;
    }
    public void setPhosphorus(double phosphorus) {
        this.phosphorus = phosphorus;
    }
    public void setMagnesium(double magnesium) {
        this.magnesium = magnesium;
    }
    public void setSodium(double sodium) {
        this.sodium = sodium;
    }
    public void setPotassium(double potassium) {
        this.potassium = potassium;
    }
    public void setIron(double iron) {
        this.iron = iron;
    }
    public void setSink(double sink) {
        this.sink = sink;
    }
    public void setCopper(double copper) {
        this.copper = copper;
    }
    public void setIodine(double iodine) {
        this.iodine = iodine;
    }
    public void setSelenium(double selenium) {
        this.selenium = selenium;
    }
    public void setCisPolyunsaturatedFattyAcidsNeg6(double cisPolyunsaturatedFattyAcidsNeg6) {
        this.cisPolyunsaturatedFattyAcidsNeg6 = cisPolyunsaturatedFattyAcidsNeg6;
    }
    public void setCisPolyunsaturatedFattyAcidsNeg3(double cisPolyunsaturatedFattyAcidsNeg3) {
        this.cisPolyunsaturatedFattyAcidsNeg3 = cisPolyunsaturatedFattyAcidsNeg3;
    }
}
