package com.nicchagil.orm.entity;

import java.util.ArrayList;
import java.util.List;

public class MessageSendLogExample {
    protected String orderByClause;

    protected boolean distinct;

    protected List<Criteria> oredCriteria;

    public MessageSendLogExample() {
        oredCriteria = new ArrayList<Criteria>();
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return distinct;
    }

    public List<Criteria> getOredCriteria() {
        return oredCriteria;
    }

    public void or(Criteria criteria) {
        oredCriteria.add(criteria);
    }

    public Criteria or() {
        Criteria criteria = createCriteriaInternal();
        oredCriteria.add(criteria);
        return criteria;
    }

    public Criteria createCriteria() {
        Criteria criteria = createCriteriaInternal();
        if (oredCriteria.size() == 0) {
            oredCriteria.add(criteria);
        }
        return criteria;
    }

    protected Criteria createCriteriaInternal() {
        Criteria criteria = new Criteria();
        return criteria;
    }

    public void clear() {
        oredCriteria.clear();
        orderByClause = null;
        distinct = false;
    }

    protected abstract static class GeneratedCriteria {
        protected List<Criterion> criteria;

        protected GeneratedCriteria() {
            super();
            criteria = new ArrayList<Criterion>();
        }

        public boolean isValid() {
            return criteria.size() > 0;
        }

        public List<Criterion> getAllCriteria() {
            return criteria;
        }

        public List<Criterion> getCriteria() {
            return criteria;
        }

        protected void addCriterion(String condition) {
            if (condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            }
            criteria.add(new Criterion(condition));
        }

        protected void addCriterion(String condition, Object value, String property) {
            if (value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value));
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if (value1 == null || value2 == null) {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
            criteria.add(new Criterion(condition, value1, value2));
        }

        public Criteria andIdIsNull() {
            addCriterion("id is null");
            return (Criteria) this;
        }

        public Criteria andIdIsNotNull() {
            addCriterion("id is not null");
            return (Criteria) this;
        }

        public Criteria andIdEqualTo(Integer value) {
            addCriterion("id =", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotEqualTo(Integer value) {
            addCriterion("id <>", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThan(Integer value) {
            addCriterion("id >", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdGreaterThanOrEqualTo(Integer value) {
            addCriterion("id >=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThan(Integer value) {
            addCriterion("id <", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdLessThanOrEqualTo(Integer value) {
            addCriterion("id <=", value, "id");
            return (Criteria) this;
        }

        public Criteria andIdIn(List<Integer> values) {
            addCriterion("id in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotIn(List<Integer> values) {
            addCriterion("id not in", values, "id");
            return (Criteria) this;
        }

        public Criteria andIdBetween(Integer value1, Integer value2) {
            addCriterion("id between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andIdNotBetween(Integer value1, Integer value2) {
            addCriterion("id not between", value1, value2, "id");
            return (Criteria) this;
        }

        public Criteria andTypeIsNull() {
            addCriterion("type is null");
            return (Criteria) this;
        }

        public Criteria andTypeIsNotNull() {
            addCriterion("type is not null");
            return (Criteria) this;
        }

        public Criteria andTypeEqualTo(String value) {
            addCriterion("type =", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotEqualTo(String value) {
            addCriterion("type <>", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThan(String value) {
            addCriterion("type >", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeGreaterThanOrEqualTo(String value) {
            addCriterion("type >=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThan(String value) {
            addCriterion("type <", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLessThanOrEqualTo(String value) {
            addCriterion("type <=", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeLike(String value) {
            addCriterion("type like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotLike(String value) {
            addCriterion("type not like", value, "type");
            return (Criteria) this;
        }

        public Criteria andTypeIn(List<String> values) {
            addCriterion("type in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotIn(List<String> values) {
            addCriterion("type not in", values, "type");
            return (Criteria) this;
        }

        public Criteria andTypeBetween(String value1, String value2) {
            addCriterion("type between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andTypeNotBetween(String value1, String value2) {
            addCriterion("type not between", value1, value2, "type");
            return (Criteria) this;
        }

        public Criteria andRecipientsIsNull() {
            addCriterion("recipients is null");
            return (Criteria) this;
        }

        public Criteria andRecipientsIsNotNull() {
            addCriterion("recipients is not null");
            return (Criteria) this;
        }

        public Criteria andRecipientsEqualTo(String value) {
            addCriterion("recipients =", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsNotEqualTo(String value) {
            addCriterion("recipients <>", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsGreaterThan(String value) {
            addCriterion("recipients >", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsGreaterThanOrEqualTo(String value) {
            addCriterion("recipients >=", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsLessThan(String value) {
            addCriterion("recipients <", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsLessThanOrEqualTo(String value) {
            addCriterion("recipients <=", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsLike(String value) {
            addCriterion("recipients like", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsNotLike(String value) {
            addCriterion("recipients not like", value, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsIn(List<String> values) {
            addCriterion("recipients in", values, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsNotIn(List<String> values) {
            addCriterion("recipients not in", values, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsBetween(String value1, String value2) {
            addCriterion("recipients between", value1, value2, "recipients");
            return (Criteria) this;
        }

        public Criteria andRecipientsNotBetween(String value1, String value2) {
            addCriterion("recipients not between", value1, value2, "recipients");
            return (Criteria) this;
        }

        public Criteria andCcsIsNull() {
            addCriterion("ccs is null");
            return (Criteria) this;
        }

        public Criteria andCcsIsNotNull() {
            addCriterion("ccs is not null");
            return (Criteria) this;
        }

        public Criteria andCcsEqualTo(String value) {
            addCriterion("ccs =", value, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsNotEqualTo(String value) {
            addCriterion("ccs <>", value, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsGreaterThan(String value) {
            addCriterion("ccs >", value, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsGreaterThanOrEqualTo(String value) {
            addCriterion("ccs >=", value, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsLessThan(String value) {
            addCriterion("ccs <", value, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsLessThanOrEqualTo(String value) {
            addCriterion("ccs <=", value, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsLike(String value) {
            addCriterion("ccs like", value, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsNotLike(String value) {
            addCriterion("ccs not like", value, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsIn(List<String> values) {
            addCriterion("ccs in", values, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsNotIn(List<String> values) {
            addCriterion("ccs not in", values, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsBetween(String value1, String value2) {
            addCriterion("ccs between", value1, value2, "ccs");
            return (Criteria) this;
        }

        public Criteria andCcsNotBetween(String value1, String value2) {
            addCriterion("ccs not between", value1, value2, "ccs");
            return (Criteria) this;
        }

        public Criteria andTitleIsNull() {
            addCriterion("title is null");
            return (Criteria) this;
        }

        public Criteria andTitleIsNotNull() {
            addCriterion("title is not null");
            return (Criteria) this;
        }

        public Criteria andTitleEqualTo(String value) {
            addCriterion("title =", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotEqualTo(String value) {
            addCriterion("title <>", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThan(String value) {
            addCriterion("title >", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleGreaterThanOrEqualTo(String value) {
            addCriterion("title >=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThan(String value) {
            addCriterion("title <", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLessThanOrEqualTo(String value) {
            addCriterion("title <=", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleLike(String value) {
            addCriterion("title like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotLike(String value) {
            addCriterion("title not like", value, "title");
            return (Criteria) this;
        }

        public Criteria andTitleIn(List<String> values) {
            addCriterion("title in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotIn(List<String> values) {
            addCriterion("title not in", values, "title");
            return (Criteria) this;
        }

        public Criteria andTitleBetween(String value1, String value2) {
            addCriterion("title between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTitleNotBetween(String value1, String value2) {
            addCriterion("title not between", value1, value2, "title");
            return (Criteria) this;
        }

        public Criteria andTriesIsNull() {
            addCriterion("tries is null");
            return (Criteria) this;
        }

        public Criteria andTriesIsNotNull() {
            addCriterion("tries is not null");
            return (Criteria) this;
        }

        public Criteria andTriesEqualTo(Integer value) {
            addCriterion("tries =", value, "tries");
            return (Criteria) this;
        }

        public Criteria andTriesNotEqualTo(Integer value) {
            addCriterion("tries <>", value, "tries");
            return (Criteria) this;
        }

        public Criteria andTriesGreaterThan(Integer value) {
            addCriterion("tries >", value, "tries");
            return (Criteria) this;
        }

        public Criteria andTriesGreaterThanOrEqualTo(Integer value) {
            addCriterion("tries >=", value, "tries");
            return (Criteria) this;
        }

        public Criteria andTriesLessThan(Integer value) {
            addCriterion("tries <", value, "tries");
            return (Criteria) this;
        }

        public Criteria andTriesLessThanOrEqualTo(Integer value) {
            addCriterion("tries <=", value, "tries");
            return (Criteria) this;
        }

        public Criteria andTriesIn(List<Integer> values) {
            addCriterion("tries in", values, "tries");
            return (Criteria) this;
        }

        public Criteria andTriesNotIn(List<Integer> values) {
            addCriterion("tries not in", values, "tries");
            return (Criteria) this;
        }

        public Criteria andTriesBetween(Integer value1, Integer value2) {
            addCriterion("tries between", value1, value2, "tries");
            return (Criteria) this;
        }

        public Criteria andTriesNotBetween(Integer value1, Integer value2) {
            addCriterion("tries not between", value1, value2, "tries");
            return (Criteria) this;
        }

        public Criteria andTrieslimitIsNull() {
            addCriterion("triesLimit is null");
            return (Criteria) this;
        }

        public Criteria andTrieslimitIsNotNull() {
            addCriterion("triesLimit is not null");
            return (Criteria) this;
        }

        public Criteria andTrieslimitEqualTo(Integer value) {
            addCriterion("triesLimit =", value, "trieslimit");
            return (Criteria) this;
        }

        public Criteria andTrieslimitNotEqualTo(Integer value) {
            addCriterion("triesLimit <>", value, "trieslimit");
            return (Criteria) this;
        }

        public Criteria andTrieslimitGreaterThan(Integer value) {
            addCriterion("triesLimit >", value, "trieslimit");
            return (Criteria) this;
        }

        public Criteria andTrieslimitGreaterThanOrEqualTo(Integer value) {
            addCriterion("triesLimit >=", value, "trieslimit");
            return (Criteria) this;
        }

        public Criteria andTrieslimitLessThan(Integer value) {
            addCriterion("triesLimit <", value, "trieslimit");
            return (Criteria) this;
        }

        public Criteria andTrieslimitLessThanOrEqualTo(Integer value) {
            addCriterion("triesLimit <=", value, "trieslimit");
            return (Criteria) this;
        }

        public Criteria andTrieslimitIn(List<Integer> values) {
            addCriterion("triesLimit in", values, "trieslimit");
            return (Criteria) this;
        }

        public Criteria andTrieslimitNotIn(List<Integer> values) {
            addCriterion("triesLimit not in", values, "trieslimit");
            return (Criteria) this;
        }

        public Criteria andTrieslimitBetween(Integer value1, Integer value2) {
            addCriterion("triesLimit between", value1, value2, "trieslimit");
            return (Criteria) this;
        }

        public Criteria andTrieslimitNotBetween(Integer value1, Integer value2) {
            addCriterion("triesLimit not between", value1, value2, "trieslimit");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgIsNull() {
            addCriterion("exception_msg is null");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgIsNotNull() {
            addCriterion("exception_msg is not null");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgEqualTo(String value) {
            addCriterion("exception_msg =", value, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgNotEqualTo(String value) {
            addCriterion("exception_msg <>", value, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgGreaterThan(String value) {
            addCriterion("exception_msg >", value, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgGreaterThanOrEqualTo(String value) {
            addCriterion("exception_msg >=", value, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgLessThan(String value) {
            addCriterion("exception_msg <", value, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgLessThanOrEqualTo(String value) {
            addCriterion("exception_msg <=", value, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgLike(String value) {
            addCriterion("exception_msg like", value, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgNotLike(String value) {
            addCriterion("exception_msg not like", value, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgIn(List<String> values) {
            addCriterion("exception_msg in", values, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgNotIn(List<String> values) {
            addCriterion("exception_msg not in", values, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgBetween(String value1, String value2) {
            addCriterion("exception_msg between", value1, value2, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andExceptionMsgNotBetween(String value1, String value2) {
            addCriterion("exception_msg not between", value1, value2, "exceptionMsg");
            return (Criteria) this;
        }

        public Criteria andStatusIsNull() {
            addCriterion("status is null");
            return (Criteria) this;
        }

        public Criteria andStatusIsNotNull() {
            addCriterion("status is not null");
            return (Criteria) this;
        }

        public Criteria andStatusEqualTo(String value) {
            addCriterion("status =", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotEqualTo(String value) {
            addCriterion("status <>", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThan(String value) {
            addCriterion("status >", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusGreaterThanOrEqualTo(String value) {
            addCriterion("status >=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThan(String value) {
            addCriterion("status <", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLessThanOrEqualTo(String value) {
            addCriterion("status <=", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusLike(String value) {
            addCriterion("status like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotLike(String value) {
            addCriterion("status not like", value, "status");
            return (Criteria) this;
        }

        public Criteria andStatusIn(List<String> values) {
            addCriterion("status in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotIn(List<String> values) {
            addCriterion("status not in", values, "status");
            return (Criteria) this;
        }

        public Criteria andStatusBetween(String value1, String value2) {
            addCriterion("status between", value1, value2, "status");
            return (Criteria) this;
        }

        public Criteria andStatusNotBetween(String value1, String value2) {
            addCriterion("status not between", value1, value2, "status");
            return (Criteria) this;
        }
    }

    public static class Criteria extends GeneratedCriteria {

        protected Criteria() {
            super();
        }
    }

    public static class Criterion {
        private String condition;

        private Object value;

        private Object secondValue;

        private boolean noValue;

        private boolean singleValue;

        private boolean betweenValue;

        private boolean listValue;

        private String typeHandler;

        public String getCondition() {
            return condition;
        }

        public Object getValue() {
            return value;
        }

        public Object getSecondValue() {
            return secondValue;
        }

        public boolean isNoValue() {
            return noValue;
        }

        public boolean isSingleValue() {
            return singleValue;
        }

        public boolean isBetweenValue() {
            return betweenValue;
        }

        public boolean isListValue() {
            return listValue;
        }

        public String getTypeHandler() {
            return typeHandler;
        }

        protected Criterion(String condition) {
            super();
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if (value instanceof List<?>) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }
        }

        protected Criterion(String condition, Object value) {
            this(condition, value, null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            super();
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, null);
        }
    }
}