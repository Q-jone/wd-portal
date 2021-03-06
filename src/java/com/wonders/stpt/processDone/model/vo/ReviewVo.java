/**
 * 
 */
package com.wonders.stpt.processDone.model.vo;


import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;


/** 
 * @ClassName: RecvBo 
 * @Description: TODO(这里用一句话描述这个类的作用) 
 * @author zhoushun 
 * @date 2013-7-19 上午9:38:00 
 *  
 */
@XmlRootElement(name = "ddd")
@XmlAccessorType(XmlAccessType.FIELD)
public class ReviewVo{
	/** */
	/**
	 * 主键.
	 */
	
	private String id = "";
	/** */
	/**
	 * 项目名称.
	 */
	
	private String projectName = "";
	/** */
	/**
	 * 项目编号.
	 */
	
	private String projectIdentifier = "";
	/** */
	/**
	 * 项目批文号.
	 */
	
	private String projectNum = "";
	/** */
	/**
	 * 项目公司.
	 */
	
	private String projectCompany = "";
	/** */
	
	private String projectChargeDept = "";
	/**
	 * 项目公司负责人.
	 */
	
	private String projectCharge = "";
	/** */
	/**
	 * 合同编号.
	 */
	
	private String contractIdentifier = "";
	/** */
	/**
	 * 自有编号.
	 */
	
	private String contractSelfNum = "";
	/** */
	/**
	 * 合同名称.
	 */
	
	private String contractName = "";
	/** */
	/**
	 * 合同金额.
	 */
	
	private String contractMoney = "";
	/** */
	/**
	 * 合同金额分类（下拉框）总价闭口 单价核算 对方支付.
	 */
	
	private String contractMoneyType = "";
	/** */
	/**
	 * 合同预算.
	 */
	
	private String contractBudget = "";
	/** */
	/**
	 * 采购方式.
	 */
	
	private String purchaseType = "";
	/** */
	/**
	 * 合同分类1.
	 */
	
	private String contractType1 = "";
	/** */
	/**
	 * 合同分类2.
	 */
	
	private String contractType2 = "";
	/** */
	/**
	 * 合同分类.
	 */
	
	private String contractType = "";
	/** */
	/**
	 * 合同分组.
	 */
	
	private String contractGroup = "";
	/** */
	/**
	 * 对方公司.
	 */
	
	private String oppositeCompany = "";
	/** */
	/**
	 * 经办人.
	 */
	
	private String dealPerson = "";
	/** */
	/**
	 * 通过时间.
	 */
	
	private String passTime = "";
	/** */
	/**
	 * 签约时间.
	 */
	
	private String signTime = "";
	/** */
	/**
	 * 履行期限开始.
	 */
	
	private String execPeriodStart = "";
	/** */
	/**
	 * 履行期限结束.
	 */
	
	private String execPeriodEnd = "";
	/** */
	/**
	 * 经办部门意见.
	 */
	
	private String dealDeptSuggest = "";
	/** */
	/**
	 * 备注.
	 */
	
	private String remark = "";
	/** */
	/**
	 * 附件.
	 */
	
	/** */
	/**
	 */
	
	private String contractType1Id = "";
	/** */
	/**
	 */
	
	private String contractType2Id ="";
	/** */
	/**
	 */
	
	private String contractGroupId = "";
	/** */
	/**
	 */
	
	private String purchaseTypeId = "";
	/** */
	/**
	 */
	
	private String contractMoneyTypeId = "";

	
	private String moneySource = "";

    private String registerDeptId = "";
    
    private String registerDeptName = "";

    private String budgetType = "";
    private String budgetTypeCode = "";
    private String statType = "";


    public String getregisterDeptId() {
        return registerDeptId;
    }

    public void setregisterDeptId(String registerDeptId) {
        this.registerDeptId = registerDeptId;
    }

    public String getRegisterDeptName() {
        return registerDeptName;
    }

    public void setRegisterDeptName(String registerDeptName) {
        this.registerDeptName = registerDeptName;
    }

    public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public String getProjectName() {
		return projectName;
	}


	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}


	public String getProjectIdentifier() {
		return projectIdentifier;
	}


	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}


	public String getProjectNum() {
		return projectNum;
	}


	public void setProjectNum(String projectNum) {
		this.projectNum = projectNum;
	}


	public String getProjectCompany() {
		return projectCompany;
	}


	public void setProjectCompany(String projectCompany) {
		this.projectCompany = projectCompany;
	}


	public String getProjectChargeDept() {
		return projectChargeDept;
	}


	public void setProjectChargeDept(String projectChargeDept) {
		this.projectChargeDept = projectChargeDept;
	}


	public String getProjectCharge() {
		return projectCharge;
	}


	public void setProjectCharge(String projectCharge) {
		this.projectCharge = projectCharge;
	}


	public String getContractIdentifier() {
		return contractIdentifier;
	}


	public void setContractIdentifier(String contractIdentifier) {
		this.contractIdentifier = contractIdentifier;
	}


	public String getContractSelfNum() {
		return contractSelfNum;
	}


	public void setContractSelfNum(String contractSelfNum) {
		this.contractSelfNum = contractSelfNum;
	}


	public String getContractName() {
		return contractName;
	}


	public void setContractName(String contractName) {
		this.contractName = contractName;
	}


	public String getContractMoney() {
		return contractMoney;
	}


	public void setContractMoney(String contractMoney) {
		this.contractMoney = contractMoney;
	}


	public String getContractMoneyType() {
		return contractMoneyType;
	}


	public void setContractMoneyType(String contractMoneyType) {
		this.contractMoneyType = contractMoneyType;
	}


	public String getContractBudget() {
		return contractBudget;
	}


	public void setContractBudget(String contractBudget) {
		this.contractBudget = contractBudget;
	}


	public String getPurchaseType() {
		return purchaseType;
	}


	public void setPurchaseType(String purchaseType) {
		this.purchaseType = purchaseType;
	}


	public String getContractType1() {
		return contractType1;
	}


	public void setContractType1(String contractType1) {
		this.contractType1 = contractType1;
	}


	public String getContractType2() {
		return contractType2;
	}


	public void setContractType2(String contractType2) {
		this.contractType2 = contractType2;
	}


	public String getContractType() {
		return contractType;
	}


	public void setContractType(String contractType) {
		this.contractType = contractType;
	}


	public String getContractGroup() {
		return contractGroup;
	}


	public void setContractGroup(String contractGroup) {
		this.contractGroup = contractGroup;
	}


	public String getOppositeCompany() {
		return oppositeCompany;
	}


	public void setOppositeCompany(String oppositeCompany) {
		this.oppositeCompany = oppositeCompany;
	}


	public String getDealPerson() {
		return dealPerson;
	}


	public void setDealPerson(String dealPerson) {
		this.dealPerson = dealPerson;
	}


	public String getPassTime() {
		return passTime;
	}


	public void setPassTime(String passTime) {
		this.passTime = passTime;
	}


	public String getSignTime() {
		return signTime;
	}


	public void setSignTime(String signTime) {
		this.signTime = signTime;
	}


	public String getExecPeriodStart() {
		return execPeriodStart;
	}


	public void setExecPeriodStart(String execPeriodStart) {
		this.execPeriodStart = execPeriodStart;
	}


	public String getExecPeriodEnd() {
		return execPeriodEnd;
	}


	public void setExecPeriodEnd(String execPeriodEnd) {
		this.execPeriodEnd = execPeriodEnd;
	}


	public String getDealDeptSuggest() {
		return dealDeptSuggest;
	}


	public void setDealDeptSuggest(String dealDeptSuggest) {
		this.dealDeptSuggest = dealDeptSuggest;
	}


	public String getRemark() {
		return remark;
	}


	public void setRemark(String remark) {
		this.remark = remark;
	}


	public String getContractType1Id() {
		return contractType1Id;
	}


	public void setContractType1Id(String contractType1Id) {
		this.contractType1Id = contractType1Id;
	}


	public String getContractType2Id() {
		return contractType2Id;
	}


	public void setContractType2Id(String contractType2Id) {
		this.contractType2Id = contractType2Id;
	}


	public String getContractGroupId() {
		return contractGroupId;
	}


	public void setContractGroupId(String contractGroupId) {
		this.contractGroupId = contractGroupId;
	}


	public String getPurchaseTypeId() {
		return purchaseTypeId;
	}


	public void setPurchaseTypeId(String purchaseTypeId) {
		this.purchaseTypeId = purchaseTypeId;
	}


	public String getContractMoneyTypeId() {
		return contractMoneyTypeId;
	}


	public void setContractMoneyTypeId(String contractMoneyTypeId) {
		this.contractMoneyTypeId = contractMoneyTypeId;
	}


	public String getMoneySource() {
		return moneySource;
	}


	public void setMoneySource(String moneySource) {
		this.moneySource = moneySource;
	}

    public String getBudgetType() {
        return budgetType;
    }

    public void setBudgetType(String budgetType) {
        this.budgetType = budgetType;
    }

    public String getBudgetTypeCode() {
        return budgetTypeCode;
    }

    public void setBudgetTypeCode(String budgetTypeCode) {
        this.budgetTypeCode = budgetTypeCode;
    }

    public String getStatType() {
        return statType;
    }

    public void setStatType(String statType) {
        this.statType = statType;
    }
	
}
