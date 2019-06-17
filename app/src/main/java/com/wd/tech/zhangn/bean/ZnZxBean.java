package com.wd.tech.zhangn.bean;

import com.chad.library.adapter.base.entity.MultiItemEntity;

import java.util.List;

public class ZnZxBean {

    /**
     * result : [{"collection":118,"id":63,"releaseTime":1553064683000,"share":39,"source":"哈哈网","summary":"我们不再依靠大量查询来寻求自己需要的知识，而是引导更多信息主动呈现在我们面前。","thumbnail":"http://img.zhiding.cn/5/473/liiX2naemnf3A.jpg?rand=187","title":"脱离时代需求的搜索引擎，会不会被人工智能APP取代？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":89,"id":62,"releaseTime":1553064558000,"share":22,"source":"亚马逊网","summary":"早在 2017 年，笔者就曾在一篇文章中断言，AI 将成为支撑亚马逊竞争优势的重要基础。就拿\u201c亚马逊进军医疗保健行业\u201d这一\u201c旧闻\u201d来说，鲜有人意识到，AI 技术将在这一波跨界尝试中扮演关键角色。","thumbnail":"http://img.zhiding.cn/5/498/liK114PK66S2.jpg?rand=154","title":"亚马逊：用AI瞄准全球10万亿美元的医疗健康大机遇","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":73,"id":61,"releaseTime":1553064018000,"share":15,"source":"雨天","summary":"主流在线法律服务正在认真利用加密货币实现部分事务的自动化处理，同时降低所有用户的服务享受门槛。","thumbnail":"http://img.zhiding.cn/5/140/li9bFzxtlSE4k.jpg?rand=136","title":"区块链如何帮助人们更方便搞定法律服务？","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2},{"collection":0,"id":0,"infoAdvertisingVo":{"content":"八维教育","id":1,"pic":"http://mobile.bwstudent.com/images/tech/ad/bw.png","url":"http://www.bwie.com"},"share":0,"whetherAdvertising":1,"whetherCollection":0,"whetherPay":0},{"collection":42,"id":59,"releaseTime":1553063316000,"share":8,"source":"科技行者","summary":"2019MWC上，中国移动以\u201c智慧连接 点亮美好未来\u201d为主题参展，围绕5G技术、物联网、数字家庭、国际业务、终端等领域，展示了最新技术、产品、5G发展计划及首款自主品牌5G终端\u201c先行者一号\u201d等。","thumbnail":"http://img.zhiding.cn/5/474/lixeaV84DvtM.jpg?rand=116","title":"中国移动亮相2019世界移动大会 展示5G发展计划并推出首款自主品牌5G终端","whetherAdvertising":2,"whetherCollection":2,"whetherPay":2}]
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

    public static  class ResultBean implements MultiItemEntity {
        /**
         * collection : 118
         * id : 63
         * releaseTime : 1553064683000
         * share : 39
         * source : 哈哈网
         * summary : 我们不再依靠大量查询来寻求自己需要的知识，而是引导更多信息主动呈现在我们面前。
         * thumbnail : http://img.zhiding.cn/5/473/liiX2naemnf3A.jpg?rand=187
         * title : 脱离时代需求的搜索引擎，会不会被人工智能APP取代？
         * whetherAdvertising : 2
         * whetherCollection : 2
         * whetherPay : 2
         * infoAdvertisingVo : {"content":"八维教育","id":1,"pic":"http://mobile.bwstudent.com/images/tech/ad/bw.png","url":"http://www.bwie.com"}
         */

        private int collection;
        private int id;
        private long releaseTime;
        private int share;
        private String source;
        private String summary;
        private String thumbnail;
        private String title;
        private int whetherAdvertising;
        private int whetherCollection;
        private int whetherPay;
        private InfoAdvertisingVoBean infoAdvertisingVo;

        public static int type_1=0;
        public static int type_2=1;

        private int type=0;
        public int getType() {
            return type;
        }
        public void setType(int type) {
            this.type = type;
        }

        @Override
        public int getItemType() {
            return type;
        }




        public int getCollection() {
            return collection;
        }

        public void setCollection(int collection) {
            this.collection = collection;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public long getReleaseTime() {
            return releaseTime;
        }

        public void setReleaseTime(long releaseTime) {
            this.releaseTime = releaseTime;
        }

        public int getShare() {
            return share;
        }

        public void setShare(int share) {
            this.share = share;
        }

        public String getSource() {
            return source;
        }

        public void setSource(String source) {
            this.source = source;
        }

        public String getSummary() {
            return summary;
        }

        public void setSummary(String summary) {
            this.summary = summary;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getWhetherAdvertising() {
            return whetherAdvertising;
        }

        public void setWhetherAdvertising(int whetherAdvertising) {
            this.whetherAdvertising = whetherAdvertising;
        }

        public int getWhetherCollection() {
            return whetherCollection;
        }

        public void setWhetherCollection(int whetherCollection) {
            this.whetherCollection = whetherCollection;
        }

        public int getWhetherPay() {
            return whetherPay;
        }

        public void setWhetherPay(int whetherPay) {
            this.whetherPay = whetherPay;
        }

        public InfoAdvertisingVoBean getInfoAdvertisingVo() {
            return infoAdvertisingVo;
        }

        public void setInfoAdvertisingVo(InfoAdvertisingVoBean infoAdvertisingVo) {
            this.infoAdvertisingVo = infoAdvertisingVo;
        }




        public static class InfoAdvertisingVoBean {
            /**
             * content : 八维教育
             * id : 1
             * pic : http://mobile.bwstudent.com/images/tech/ad/bw.png
             * url : http://www.bwie.com
             */

            private String content;
            private int id;
            private String pic;
            private String url;

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPic() {
                return pic;
            }

            public void setPic(String pic) {
                this.pic = pic;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
