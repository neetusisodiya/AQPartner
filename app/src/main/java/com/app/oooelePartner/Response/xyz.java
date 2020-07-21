package com.app.oooelePartner.Response;

import java.util.List;

public class xyz {


    /**
     * status : true
     * data : [{"day":"Monday","time":[{"time":"8-9 AM","status":"yes","id":3},{"time":"9-10 AM","status":"no","id":0},{"time":"10-11 AM","status":"no","id":0},{"time":"11-12 AM","status":"no","id":0},{"time":"12-1 PM","status":"no","id":0},{"time":"1-2 PM","status":"no","id":0},{"time":"2-3 PM","status":"no","id":0},{"time":"3-4 PM","status":"no","id":0},{"time":"4-5 PM","status":"no","id":0},{"time":"5-6 PM","status":"no","id":0},{"time":"6-7 PM","status":"no","id":0},{"time":"7-8 PM","status":"yes","id":1}]},{"day":"Tuesday","time":[{"time":"8-9 AM","status":"no","id":0},{"time":"9-10 AM","status":"no","id":0},{"time":"10-11 AM","status":"no","id":0},{"time":"11-12 AM","status":"no","id":0},{"time":"12-1 PM","status":"no","id":0},{"time":"1-2 PM","status":"no","id":0},{"time":"2-3 PM","status":"no","id":0},{"time":"3-4 PM","status":"no","id":0},{"time":"4-5 PM","status":"no","id":0},{"time":"5-6 PM","status":"no","id":0},{"time":"6-7 PM","status":"no","id":0},{"time":"7-8 PM","status":"no","id":0}]},{"day":"Wednesday","time":[{"time":"8-9 AM","status":"no","id":0},{"time":"9-10 AM","status":"no","id":0},{"time":"10-11 AM","status":"no","id":0},{"time":"11-12 AM","status":"no","id":0},{"time":"12-1 PM","status":"no","id":0},{"time":"1-2 PM","status":"no","id":0},{"time":"2-3 PM","status":"no","id":0},{"time":"3-4 PM","status":"no","id":0},{"time":"4-5 PM","status":"no","id":0},{"time":"5-6 PM","status":"no","id":0},{"time":"6-7 PM","status":"no","id":0},{"time":"7-8 PM","status":"no","id":0}]},{"day":"Thursday","time":[{"time":"8-9 AM","status":"no","id":0},{"time":"9-10 AM","status":"no","id":0},{"time":"10-11 AM","status":"no","id":0},{"time":"11-12 AM","status":"no","id":0},{"time":"12-1 PM","status":"no","id":0},{"time":"1-2 PM","status":"no","id":0},{"time":"2-3 PM","status":"no","id":0},{"time":"3-4 PM","status":"no","id":0},{"time":"4-5 PM","status":"no","id":0},{"time":"5-6 PM","status":"no","id":0},{"time":"6-7 PM","status":"no","id":0},{"time":"7-8 PM","status":"no","id":0}]},{"day":"Friday","time":[{"time":"8-9 AM","status":"no","id":0},{"time":"9-10 AM","status":"no","id":0},{"time":"10-11 AM","status":"no","id":0},{"time":"11-12 AM","status":"no","id":0},{"time":"12-1 PM","status":"no","id":0},{"time":"1-2 PM","status":"no","id":0},{"time":"2-3 PM","status":"no","id":0},{"time":"3-4 PM","status":"no","id":0},{"time":"4-5 PM","status":"no","id":0},{"time":"5-6 PM","status":"no","id":0},{"time":"6-7 PM","status":"no","id":0},{"time":"7-8 PM","status":"no","id":0}]},{"day":"Saturday","time":[{"time":"8-9 AM","status":"no","id":0},{"time":"9-10 AM","status":"no","id":0},{"time":"10-11 AM","status":"no","id":0},{"time":"11-12 AM","status":"no","id":0},{"time":"12-1 PM","status":"no","id":0},{"time":"1-2 PM","status":"no","id":0},{"time":"2-3 PM","status":"no","id":0},{"time":"3-4 PM","status":"no","id":0},{"time":"4-5 PM","status":"no","id":0},{"time":"5-6 PM","status":"no","id":0},{"time":"6-7 PM","status":"no","id":0},{"time":"7-8 PM","status":"no","id":0}]},{"day":"Sunday","time":[{"time":"8-9 AM","status":"no","id":0},{"time":"9-10 AM","status":"no","id":0},{"time":"10-11 AM","status":"no","id":0},{"time":"11-12 AM","status":"no","id":0},{"time":"12-1 PM","status":"no","id":0},{"time":"1-2 PM","status":"no","id":0},{"time":"2-3 PM","status":"no","id":0},{"time":"3-4 PM","status":"no","id":0},{"time":"4-5 PM","status":"no","id":0},{"time":"5-6 PM","status":"no","id":0},{"time":"6-7 PM","status":"no","id":0},{"time":"7-8 PM","status":"no","id":0}]}]
     */

    private boolean status;
    private List<DataBean> data;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * day : Monday
         * time : [{"time":"8-9 AM","status":"yes","id":3},{"time":"9-10 AM","status":"no","id":0},{"time":"10-11 AM","status":"no","id":0},{"time":"11-12 AM","status":"no","id":0},{"time":"12-1 PM","status":"no","id":0},{"time":"1-2 PM","status":"no","id":0},{"time":"2-3 PM","status":"no","id":0},{"time":"3-4 PM","status":"no","id":0},{"time":"4-5 PM","status":"no","id":0},{"time":"5-6 PM","status":"no","id":0},{"time":"6-7 PM","status":"no","id":0},{"time":"7-8 PM","status":"yes","id":1}]
         */

        private String day;
        private List<TimeBean> time;

        public String getDay() {
            return day;
        }

        public void setDay(String day) {
            this.day = day;
        }

        public List<TimeBean> getTime() {
            return time;
        }

        public void setTime(List<TimeBean> time) {
            this.time = time;
        }

        public static class TimeBean {
            /**
             * time : 8-9 AM
             * status : yes
             * id : 3
             */

            private String time;
            private String status;
            private int id;

            public String getTime() {
                return time;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public String getStatus() {
                return status;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }
        }
    }
}
