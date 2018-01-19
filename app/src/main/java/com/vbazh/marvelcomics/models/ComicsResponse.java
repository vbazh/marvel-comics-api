package com.vbazh.marvelcomics.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ComicsResponse {

    @SerializedName("code")
    Integer code;

    @SerializedName("status")
    String status;

    @SerializedName("data")
    Data data;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        @SerializedName("offset")
        Integer offset;

        @SerializedName("limit")
        Integer limit;

        @SerializedName("total")
        Integer total;

        @SerializedName("count")
        Integer count;

        @SerializedName("results")
        List<Comic> comics;

        public Integer getOffset() {
            return offset;
        }

        public void setOffset(Integer offset) {
            this.offset = offset;
        }

        public Integer getLimit() {
            return limit;
        }

        public void setLimit(Integer limit) {
            this.limit = limit;
        }

        public Integer getTotal() {
            return total;
        }

        public void setTotal(Integer total) {
            this.total = total;
        }

        public Integer getCount() {
            return count;
        }

        public void setCount(Integer count) {
            this.count = count;
        }

        public List<Comic> getComics() {
            return comics;
        }

        public void setComics(List<Comic> comics) {
            this.comics = comics;
        }

    }

    public class Comic {

        @SerializedName("id")
        Integer id;

        @SerializedName("digitalId")
        Integer digitalId;

        @SerializedName("title")
        String title;

        @SerializedName("characters")
        CharacterList characterList;

        boolean isOpen;

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Integer getDigitalId() {
            return digitalId;
        }

        public void setDigitalId(Integer digitalId) {
            this.digitalId = digitalId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public CharacterList getCharacterList() {
            return characterList;
        }

        public void setCharacterList(CharacterList characterList) {
            this.characterList = characterList;
        }

        public Boolean getOpen() {
            return isOpen;
        }

        public void setOpen(Boolean open) {
            isOpen = open;
        }

        public class CharacterList {

            @SerializedName("available")
            Integer available;

            @SerializedName("characters")
            List<Character> characters;

            public Integer getAvailable() {
                return available;
            }

            public void setAvailable(Integer available) {
                this.available = available;
            }

            public List<Character> getCharacters() {
                return characters;
            }

            public void setCharacters(List<Character> characters) {
                this.characters = characters;
            }

            public class Character {
                @SerializedName("name")
                String name;

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }
            }
        }



    }

}


