package com.github.tomekmazurek.songsapp.model;

import javax.persistence.Entity;

public enum Category {
  ALTERNATIVE("Alternative"),
  ANIME("Anime"),
  BLUES("Blues"),
  CHILDRENSMUSIC("Children's Music"),
  CLASSICAL("Classical"),
  COMEDY("Comedy"),
  COMMERCIAL("Commercial"),
  COUNTRY("Country"),
  DANCE("Dance"),
  EASYLISTENING("Easy Listening"),
  ELECTRONIC("Electronic"),
  FOLK("Folk"),
  HIP_HOP("Hip Hop"),
  INDIE("Indie"),
  INDUSTRIAL("Industrial"),
  GOSPEL("Gospel"),
  GRUNGE("Grunge"),
  INSTRUMENTAL("Instrumental"),
  JAZZ("Jazz"),
  K_POP("K-Pop"),
  LATIN("Latin"),
  METAL("Metal"),
  NEW_AGE("New Age"),
  OPERA("Opera"),
  POP("Pop"),
  PROGRESSIVE("Progressive"),
  RNB("R&B"),
  REGGAE("Reggae"),
  ROCK("Rock"),
  SOUL("Soul"),
  TECHNO("Techno"),
  OTHER("Other");
  private String value;

  Category(String value) {
    this.value = value;
  }

  public String getValue() {
    return value;
  }
}
