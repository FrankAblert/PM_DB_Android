package com.mzba.pokemon.entity;

/**
 * Pokemon
 * Created by 06peng on 16/8/19.
 */
public class PokemonEntity extends BasicEntity {

    private int id;
    /**
     * 名字
     */
    private String name;
    /**
     * 说明
     */
    private String description;
    /**
     * 属性
     */
    private String property;
    /**
     * 特性
     */
    private String feature;
    /**
     * 隐藏特性
     */
    private String hidden_feature;
    /**
     * 分类
     */
    private String sort;
    /**
     * 身高
     */
    private String height;
    /**
     * 体重
     */
    private String weight;
    /**
     * HP
     */
    private int HP;
    /**
     * 攻击
     */
    private int attack;
    /**
     * 防御
     */
    private int defense;
    /**
     * 特攻
     */
    private int special_attack;
    /**
     * 特防
     */
    private int special_defense;
    /**
     * 速度
     */
    private int speed;
    /**
     * 总和
     */
    private int total;
    /**
     * 生蛋分组
     */
    private String egg_group;
    /**
     * 孵化步数
     */
    private String hatch_step;
    /**
     * 性别比率
     */
    private String sex_ratio;
    /**
     * 捕获度
     */
    private int capture_chance;
    /**
     * 初始亲密度
     */
    private int initial_intimacy;
    /**
     * 满级经验
     */
    private long max_experience;
    /**
     * 基础点数
     */
    private String base_points;
    /**
     * 备注
     */
    private String remarks;
    /**
     * 历史版本
     */
    private String version_history;
    /**
     * 设置资料
     */
    private String set_data;
    /**
     * 图片url
     */
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getFeature() {
        return feature;
    }

    public void setFeature(String feature) {
        this.feature = feature;
    }

    public String getHidden_feature() {
        return hidden_feature;
    }

    public void setHidden_feature(String hidden_feature) {
        this.hidden_feature = hidden_feature;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getWeight() {
        return weight;
    }

    public void setWeight(String weight) {
        this.weight = weight;
    }

    public int getHP() {
        return HP;
    }

    public void setHP(int HP) {
        this.HP = HP;
    }

    public int getAttack() {
        return attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getDefense() {
        return defense;
    }

    public void setDefense(int defense) {
        this.defense = defense;
    }

    public int getSpecial_attack() {
        return special_attack;
    }

    public void setSpecial_attack(int special_attack) {
        this.special_attack = special_attack;
    }

    public int getSpecial_defense() {
        return special_defense;
    }

    public void setSpecial_defense(int special_defense) {
        this.special_defense = special_defense;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getEgg_group() {
        return egg_group;
    }

    public void setEgg_group(String egg_group) {
        this.egg_group = egg_group;
    }

    public String getHatch_step() {
        return hatch_step;
    }

    public void setHatch_step(String hatch_step) {
        this.hatch_step = hatch_step;
    }

    public String getSex_ratio() {
        return sex_ratio;
    }

    public void setSex_ratio(String sex_ratio) {
        this.sex_ratio = sex_ratio;
    }

    public int getCapture_chance() {
        return capture_chance;
    }

    public void setCapture_chance(int capture_chance) {
        this.capture_chance = capture_chance;
    }

    public int getInitial_intimacy() {
        return initial_intimacy;
    }

    public void setInitial_intimacy(int initial_intimacy) {
        this.initial_intimacy = initial_intimacy;
    }

    public long getMax_experience() {
        return max_experience;
    }

    public void setMax_experience(long max_experience) {
        this.max_experience = max_experience;
    }

    public String getBase_points() {
        return base_points;
    }

    public void setBase_points(String base_points) {
        this.base_points = base_points;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getVersion_history() {
        return version_history;
    }

    public void setVersion_history(String version_history) {
        this.version_history = version_history;
    }

    public String getSet_data() {
        return set_data;
    }

    public void setSet_data(String set_data) {
        this.set_data = set_data;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
