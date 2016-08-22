package com.mzba.pokemon.model;

import com.mzba.pokemon.entity.PokemonEntity;
import com.mzba.pokemon.param.BasicParam;

import java.util.ArrayList;

/**
 * Model of MainActivity
 * Created by 06peng on 16/8/19.
 */
public class MainModel implements IModel {
    @Override
    public Object get(BasicParam param) {
        ArrayList<PokemonEntity> pokemonEntities = new ArrayList<>();
        PokemonEntity entity = new PokemonEntity();
        entity.setName("妙蛙种子");
        entity.setImage("http://res.pokemon.name/common/pokemon/pdw/001.00.png");
        entity.setDescription("妙蛙种子（フシギダネ、Bulbasaur）是第一世代登场的宝可梦。");
        pokemonEntities.add(entity);

        entity = new PokemonEntity();
        entity.setName("妙蛙草");
        entity.setImage("http://res.pokemon.name/common/pokemon/pdw/002.00.png");
        entity.setDescription("妙蛙草（フシギソウ、Ivysaur）是第一世代登场的宝可梦。");
        pokemonEntities.add(entity);

        entity = new PokemonEntity();
        entity.setName("妙蛙花");
        entity.setImage("http://res.pokemon.name/common/pokemon/pdw/003.00.png");
        entity.setDescription("妙蛙花（フシギバナ、Venusaur）是第一世代登场的宝可梦。");
        pokemonEntities.add(entity);

        entity = new PokemonEntity();
        entity.setName("小火龙");
        entity.setImage("http://res.pokemon.name/common/pokemon/pdw/004.00.png");
        entity.setDescription("小火龙（ヒトカゲ、Charmander）是第一世代登场的宝可梦。");
        pokemonEntities.add(entity);

        entity = new PokemonEntity();
        entity.setName("火恐龙");
        entity.setImage("http://res.pokemon.name/common/pokemon/pdw/005.00.png");
        entity.setDescription("火恐龙（リザード、Charmeleon）是第一世代登场的宝可梦。");
        pokemonEntities.add(entity);

        entity = new PokemonEntity();
        entity.setName("喷火龙");
        entity.setImage("http://res.pokemon.name/common/pokemon/pdw/006.00.png");
        entity.setDescription("喷火龙（リザードン、Charizard）是第一世代登场的宝可梦。");
        pokemonEntities.add(entity);

        entity = new PokemonEntity();
        entity.setName("杰尼龟");
        entity.setImage("http://res.pokemon.name/common/pokemon/pdw/007.00.png");
        entity.setDescription("杰尼龟（ゼニガメ、Squirtle）是第一世代登场的宝可梦。");
        pokemonEntities.add(entity);

        entity = new PokemonEntity();
        entity.setName("卡咪龟");
        entity.setImage("http://res.pokemon.name/common/pokemon/pdw/008.00.png");
        entity.setDescription("卡咪龟（カメール、Wartortle）是第一世代登场的宝可梦。");
        pokemonEntities.add(entity);

        entity = new PokemonEntity();
        entity.setName("水箭龟");
        entity.setImage("http://res.pokemon.name/common/pokemon/pdw/009.00.png");
        entity.setDescription("水箭龟（カメックス、Blastoise）是第一世代登场的宝可梦。");
        pokemonEntities.add(entity);

        return pokemonEntities;
    }

    /**
     * 分页获取
     */
    public ArrayList<PokemonEntity> getPokemons(int page, int count) {
        ArrayList<PokemonEntity> pokemonEntities = new ArrayList<>();
        //TODO
        return pokemonEntities;
    }

    /**
     * 根据关键字搜索
     * @param keyword 搜索字段为#description
     * @return
     */
    public ArrayList<PokemonEntity> searchPokemons(String keyword) {
        ArrayList<PokemonEntity> pokemonEntities = new ArrayList<>();
        //TODO
        return pokemonEntities;
    }
}
