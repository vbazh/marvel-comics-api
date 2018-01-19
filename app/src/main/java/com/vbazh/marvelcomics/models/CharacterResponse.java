package com.vbazh.marvelcomics.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CharacterResponse {

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
        List<Character> comics;

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

        public List<Character> getCharacters() {
            return comics;
        }

        public void setCharacters(List<Character> characters) {
            this.comics = characters;
        }
    }

    public class Character {

        @SerializedName("name")
        String name;
        @SerializedName("thumbnail")
        Thumbnail thumbnail;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public Thumbnail getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(Thumbnail thumbnail) {
            this.thumbnail = thumbnail;
        }
    }

    public class Thumbnail {

        @SerializedName("path")
        String path;

        @SerializedName("extension")
        String extension;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getExtension() {
            return extension;
        }

        public void setExtension(String extension) {
            this.extension = extension;
        }
    }

}
