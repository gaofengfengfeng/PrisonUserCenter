package com.gaofeng.prisonusercenter.beans.prisoner;

import java.util.List;

/**
 * @Author: gaofeng
 * @Date: 2018-08-24
 * @Description:
 */
public class FindByNameRet {
    private List<ReturnPrisoner> returnPrisonerList;

    public List<ReturnPrisoner> getReturnPrisonerList() {
        return returnPrisonerList;
    }

    public void setReturnPrisonerList(List<ReturnPrisoner> returnPrisonerList) {
        this.returnPrisonerList = returnPrisonerList;
    }

    @Override
    public String toString() {
        return "FindByNameRet{" +
                "returnPrisonerList=" + returnPrisonerList +
                '}';
    }

    public class ReturnPrisoner {
        private String prisonerName;
        private String prisonerCodeNum;

        public String getPrisonerName() {
            return prisonerName;
        }

        public void setPrisonerName(String prisonerName) {
            this.prisonerName = prisonerName;
        }

        public String getPrisonerCodeNum() {
            return prisonerCodeNum;
        }

        public void setPrisonerCodeNum(String prisonerCodeNum) {
            this.prisonerCodeNum = prisonerCodeNum;
        }

        @Override
        public String toString() {
            return "returnPrisoner{" +
                    "prisonerName='" + prisonerName + '\'' +
                    ", prisonerCodeNum='" + prisonerCodeNum + '\'' +
                    '}';
        }
    }
}
