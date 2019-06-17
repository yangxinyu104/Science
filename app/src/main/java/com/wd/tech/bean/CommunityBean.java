package com.wd.tech.bean;

import java.util.List;

public class CommunityBean {

    /**
     * result : [{"comment":1,"communityCommentVoList":[{"content":"听这么一说是不是感觉世界充满了爱","nickName":"随我去流浪_ls3","userId":769}],"content":"感觉这个社会越来越好了，大家都很懂事。男生都挺懂事，一有钱就想多照顾几个女生。\n女生也很懂事，知道男生没钱就不和这个男生在一起，免得他辛苦。","file":"http://mobile.bwstudent.com/images/tech/community_pic/2019-06-11/2163520190611095717.jpeg","headPic":"http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEIqOqk9YnjmQUAKtgTicZLxFvqMCoSXtLZebr00zwyicr0UQ32mHRNlwejL9heyMbuJucshriaMjwo2Q/132","id":1723,"nickName":"随我去流浪_ls3","praise":0,"publishTime":1560218237000,"userId":769,"power":2,"signature":"秋天不回来","whetherFollow":2,"whetherGreat":2,"whetherVip":2}]
     * message : 查询成功
     * status : 0000
     */

    private String message;
    private String status;
    private List<ResultBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * comment : 1
         * communityCommentVoList : [{"content":"听这么一说是不是感觉世界充满了爱","nickName":"随我去流浪_ls3","userId":769}]
         * content : 感觉这个社会越来越好了，大家都很懂事。男生都挺懂事，一有钱就想多照顾几个女生。
         女生也很懂事，知道男生没钱就不和这个男生在一起，免得他辛苦。
         * file : http://mobile.bwstudent.com/images/tech/community_pic/2019-06-11/2163520190611095717.jpeg
         * headPic : http://thirdwx.qlogo.cn/mmopen/vi_32/PiajxSqBRaEIqOqk9YnjmQUAKtgTicZLxFvqMCoSXtLZebr00zwyicr0UQ32mHRNlwejL9heyMbuJucshriaMjwo2Q/132
         * id : 1723
         * nickName : 随我去流浪_ls3
         * praise : 0
         * publishTime : 1560218237000
         * userId : 769
         * power : 2
         * signature : 秋天不回来
         * whetherFollow : 2
         * whetherGreat : 2
         * whetherVip : 2
         */

        private int comment;
        private String content;
        private String file;
        private String headPic;
        private int id;
        private String nickName;
        private int praise;
        private long publishTime;
        private int userId;
        private int power;
        private String signature;
        private int whetherFollow;
        private int whetherGreat;
        private int whetherVip;
        private List<CommunityCommentVoListBean> communityCommentVoList;

        public int getComment() {
            return comment;
        }

        public void setComment(int comment) {
            this.comment = comment;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getFile() {
            return file;
        }

        public void setFile(String file) {
            this.file = file;
        }

        public String getHeadPic() {
            return headPic;
        }

        public void setHeadPic(String headPic) {
            this.headPic = headPic;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getPraise() {
            return praise;
        }

        public void setPraise(int praise) {
            this.praise = praise;
        }

        public long getPublishTime() {
            return publishTime;
        }

        public void setPublishTime(long publishTime) {
            this.publishTime = publishTime;
        }

        public int getUserId() {
            return userId;
        }

        public void setUserId(int userId) {
            this.userId = userId;
        }

        public int getPower() {
            return power;
        }

        public void setPower(int power) {
            this.power = power;
        }

        public String getSignature() {
            return signature;
        }

        public void setSignature(String signature) {
            this.signature = signature;
        }

        public int getWhetherFollow() {
            return whetherFollow;
        }

        public void setWhetherFollow(int whetherFollow) {
            this.whetherFollow = whetherFollow;
        }

        public int getWhetherGreat() {
            return whetherGreat;
        }

        public void setWhetherGreat(int whetherGreat) {
            this.whetherGreat = whetherGreat;
        }

        public int getWhetherVip() {
            return whetherVip;
        }

        public void setWhetherVip(int whetherVip) {
            this.whetherVip = whetherVip;
        }

        public List<CommunityCommentVoListBean> getCommunityCommentVoList() {
            return communityCommentVoList;
        }

        public void setCommunityCommentVoList(List<CommunityCommentVoListBean> communityCommentVoList) {
            this.communityCommentVoList = communityCommentVoList;
        }

        public static class CommunityCommentVoListBean {
            /**
             * content : 听这么一说是不是感觉世界充满了爱
             * nickName : 随我去流浪_ls3
             * userId : 769
             */

            private String content;
            private String nickName;
            private int userId;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getNickName() {
                return nickName;
            }

            public void setNickName(String nickName) {
                this.nickName = nickName;
            }

            public int getUserId() {
                return userId;
            }

            public void setUserId(int userId) {
                this.userId = userId;
            }
        }
    }
}
