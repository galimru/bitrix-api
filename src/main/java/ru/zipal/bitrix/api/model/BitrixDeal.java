package ru.zipal.bitrix.api.model;

import ru.zipal.bitrix.api.common.FieldName;

import java.math.BigDecimal;
import java.util.Date;

public class BitrixDeal implements BitrixEntity {

/*
    "ID": "3",
    "TITLE": "тест",
    "TYPE_ID": "SALE",
	"STAGE_ID": "EXECUTING",
    "COMPANY_ID": "1",
    "CONTACT_ID": null,
	"CATEGORY_ID": "0",
	"CURRENCY_ID": "RUB",
	"ASSIGNED_BY_ID": "1",
	"CLOSED": "N",
	"OPENED": "Y",
	"IS_REPEATED_APPROACH": "N",
	"UTM_SOURCE": null,
	"COMMENTS": null,
	"ADDITIONAL_INFO": null,
	"IS_RECURRING": "N",
	"LEAD_ID": null,
	"UTM_MEDIUM": null,

	"UTM_CONTENT": null,
	"LOCATION_ID": null,
	"BEGINDATE": "2019-05-19T03:00:00+03:00",
	"CLOSEDATE": "2019-05-26T03:00:00+03:00",
	"IS_RETURN_CUSTOMER": "N",
	"QUOTE_ID": null,
	"DATE_CREATE": "2019-05-19T18:21:20+03:00",
	"TAX_VALUE": null,
	"SOURCE_ID": null,
	"ORIGIN_ID": null,
	"IS_NEW": "N",
	"ORIGINATOR_ID": null,
	"STAGE_SEMANTIC_ID": "P",
	"UTM_TERM": null,
	"UTM_CAMPAIGN": null,
	"PROBABILITY": null,
	"MODIFY_BY_ID": "1",
	"DATE_MODIFY": "2019-05-19T18:35:33+03:00",
	"SOURCE_DESCRIPTION": null,
	"OPPORTUNITY": "12223.00",
	"CREATED_BY_ID": "1"
 */

	@FieldName("ID")
	private Long id;
	@FieldName("TITLE")
	private String title;
	@FieldName("TYPE_ID")
	private String typeId;
	@FieldName("STAGE_ID")
	private String stageId;
	@FieldName("COMPANY_ID")
	private Integer companyId;
	@FieldName("CONTACT_ID")
	private Integer contactId;
    @FieldName("CATEGORY_ID")
    private Integer categoryId;
    @FieldName("CURRENCY_ID")
    private String currencyId;
    @FieldName("ASSIGNED_BY")
    private Integer assignedById;
    @FieldName("CLOSED")
    private Boolean closed;
	@FieldName("OPENED")
	private Boolean opened;
    @FieldName("IS_REPEATED_APPROACH")
    private Boolean isRepeatedApproach;
    @FieldName("COMMENTS")
    private String comments;
    @FieldName("ADDITIONAL_INFO")
    private String additionalInfo;
    @FieldName("IS_RECURRING")
    private Boolean isRecurring;
    @FieldName("LEAD_ID")
    private String leadId;
    @FieldName("UTM_SOURCE")
    private String utmSource;
    @FieldName("UTM_MEDIUM")
    private String utmMedium;
    @FieldName("UTM_CONTENT")
    private String utmContent;
    @FieldName("UTM_TERM")
    private String utmTerm;
    @FieldName("UTM_CAMPAIGN")
    private String utmCampaign;
    @FieldName("LOCATION_ID")
    private Integer locationId;
    @FieldName("BEGINDATE")
    private Date beginDate;
    @FieldName("CLOSEDATE")
    private Date closeDate;
    @FieldName("IS_RETURN_CUSTOMER")
    private String isReturnCustomer;
    @FieldName("QUOTE_ID")
    private String quoteId;
    @FieldName("CREATED_BY_ID")
    private Integer createdById;
    @FieldName("DATE_CREATE")
    private Date dateCreate;
    @FieldName("MODIFY_BY_ID")
    private Integer modifyById;
    @FieldName("DATE_MODIFY")
    private Date dateModify;
    @FieldName("TAX_VALUE")
    private BigDecimal taxValue;
    @FieldName("SOURCE_ID")
    private String sourceId;
    @FieldName("ORIGIN_ID")
    private String originId;
    @FieldName("IS_NEW")
    private Boolean isNew;
    @FieldName("ORIGINATOR_ID")
    private String originatorId;
    @FieldName("STAGE_SEMANTIC_ID")
    private String stageSemanticId;
    @FieldName("PROBABILITY")
    private Integer probability;
    @FieldName("SOURCE_DESCRIPTION")
    private String sourceDescription;
    @FieldName("OPPORTUNITY")
    private BigDecimal opportunity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getStageId() {
        return stageId;
    }

    public void setStageId(String stageId) {
        this.stageId = stageId;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }

    public Integer getContactId() {
        return contactId;
    }

    public void setContactId(Integer contactId) {
        this.contactId = contactId;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCurrencyId() {
        return currencyId;
    }

    public void setCurrencyId(String currencyId) {
        this.currencyId = currencyId;
    }

    public Integer getAssignedById() {
        return assignedById;
    }

    public void setAssignedById(Integer assignedById) {
        this.assignedById = assignedById;
    }

    public Boolean getClosed() {
        return closed;
    }

    public void setClosed(Boolean closed) {
        this.closed = closed;
    }

    public Boolean getOpened() {
        return opened;
    }

    public void setOpened(Boolean opened) {
        this.opened = opened;
    }

    public Boolean getRepeatedApproach() {
        return isRepeatedApproach;
    }

    public void setRepeatedApproach(Boolean repeatedApproach) {
        isRepeatedApproach = repeatedApproach;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public Boolean getRecurring() {
        return isRecurring;
    }

    public void setRecurring(Boolean recurring) {
        isRecurring = recurring;
    }

    public String getLeadId() {
        return leadId;
    }

    public void setLeadId(String leadId) {
        this.leadId = leadId;
    }

    public String getUtmSource() {
        return utmSource;
    }

    public void setUtmSource(String utmSource) {
        this.utmSource = utmSource;
    }

    public String getUtmMedium() {
        return utmMedium;
    }

    public void setUtmMedium(String utmMedium) {
        this.utmMedium = utmMedium;
    }

    public String getUtmContent() {
        return utmContent;
    }

    public void setUtmContent(String utmContent) {
        this.utmContent = utmContent;
    }

    public String getUtmTerm() {
        return utmTerm;
    }

    public void setUtmTerm(String utmTerm) {
        this.utmTerm = utmTerm;
    }

    public String getUtmCampaign() {
        return utmCampaign;
    }

    public void setUtmCampaign(String utmCampaign) {
        this.utmCampaign = utmCampaign;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getCloseDate() {
        return closeDate;
    }

    public void setCloseDate(Date closeDate) {
        this.closeDate = closeDate;
    }

    public Boolean getReturnCustomer() {
        return isReturnCustomer;
    }

    public void setReturnCustomer(Boolean returnCustomer) {
        isReturnCustomer = returnCustomer;
    }

    public String getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(String quoteId) {
        this.quoteId = quoteId;
    }

    public Integer getCreatedById() {
        return createdById;
    }

    public void setCreatedById(Integer createdById) {
        this.createdById = createdById;
    }

    public Date getDateCreate() {
        return dateCreate;
    }

    public void setDateCreate(Date dateCreate) {
        this.dateCreate = dateCreate;
    }

    public Integer getModifyById() {
        return modifyById;
    }

    public void setModifyById(Integer modifyById) {
        this.modifyById = modifyById;
    }

    public Date getDateModify() {
        return dateModify;
    }

    public void setDateModify(Date dateModify) {
        this.dateModify = dateModify;
    }

    public BigDecimal getTaxValue() {
        return taxValue;
    }

    public void setTaxValue(BigDecimal taxValue) {
        this.taxValue = taxValue;
    }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public Boolean getNew() {
        return isNew;
    }

    public void setNew(Boolean aNew) {
        isNew = aNew;
    }

    public String getOriginatorId() {
        return originatorId;
    }

    public void setOriginatorId(String originatorId) {
        this.originatorId = originatorId;
    }

    public String getStageSemanticId() {
        return stageSemanticId;
    }

    public void setStageSemanticId(String stageSemanticId) {
        this.stageSemanticId = stageSemanticId;
    }

    public Integer getProbability() {
        return probability;
    }

    public void setProbability(Integer probability) {
        this.probability = probability;
    }

    public String getSourceDescription() {
        return sourceDescription;
    }

    public void setSourceDescription(String sourceDescription) {
        this.sourceDescription = sourceDescription;
    }

    public BigDecimal getOpportunity() {
        return opportunity;
    }

    public void setOpportunity(BigDecimal opportunity) {
        this.opportunity = opportunity;
    }

    @Override
	public String toString() {
		return "BitrixDeal{" +
				"id=" + id +
				", title='" + title + '\'' +
				'}';
	}
}
