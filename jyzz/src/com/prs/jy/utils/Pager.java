package com.prs.jy.utils;

import java.util.HashMap;
import java.util.Map;

/**
 * 分页器
 *
 * @author Ouyang
 */
public class Pager {
    /**
     * 默认用每页记录数
     */
    private static final int DEFAULT_PAGE_SIZE = 30;
    private static final String DEFAULT_FORM_NAME = "pager";
    private String formName = DEFAULT_FORM_NAME;

    /**
     * 类工作用的变量
     */
    private int page = 1;
    private int pageSize = DEFAULT_PAGE_SIZE;
    private int pageCount;
    private int resultCount;
    /**
     * 存放排序用字段
     * 例如：{postDate=asc, id=desc}
     */
    private Map<String, String> orders = new HashMap<String, String>();
    /**
     * 存放查询条件
     * 例如：{username=admi, phone=139}
     */
    private Map<String, String> demand = new HashMap<String, String>();
    private boolean enabled = true;

    public Pager() {

    }

    public String getFormName() {
        return formName;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public int getResultCount() {
        return this.resultCount;
    }

    /**
     * 设置结果总数，并更新无效页码信息
     *
     * @param resultCount 结果总数
     */
    public void setResultCount(int resultCount) {
        this.resultCount = resultCount;

        this.pageSize = this.pageSize < 1 ? DEFAULT_PAGE_SIZE : this.pageSize;

        this.pageCount = (this.resultCount + this.pageSize - 1) / this.pageSize;

        this.page = this.page > this.pageCount ? this.pageCount : this.page;

        this.page = this.page < 1 ? 1 : this.page;
    }

    /*
     * 得到每页的第一条数据的索引
     */
    public int getFirstIndex() {
        if (enabled) {
            return (this.page - 1) * this.pageSize;
        } else {
            return 0;
        }
    }

    /*
    * 得到每页的最后一条数据的索引
    */
    public int getLastIndex() {
        return getPageSize() + getFirstIndex();
    }

    public Map<String, String> getOrders() {
        return orders;
    }

    public Map<String, String> getDemand() {
        return demand;
    }
    
    

    /**
     * 简单实现，待完善
     *
     * @return
     */
    /*
    public String toString() {
        Html pager = new Html("div");

        pager.addHtml(new Html("a").addAttribute("href", "?page=1").addContent("首页"));
        pager.addContent("&nbsp;|&nbsp;");
        pager.addHtml(new Html("a").addAttribute("href", "?page=" + (this.page - 1)).addContent("上一页"));
        pager.addContent("&nbsp;|&nbsp;");
        pager.addHtml(new Html("a").addAttribute("href", "?page=" + (this.page + 1)).addContent("下一页"));
        pager.addContent("&nbsp;|&nbsp;");
        pager.addHtml(new Html("a").addAttribute("href", "?page=" + this.pageSize).addContent("末页"));

        return pager.toString();
    }
    */

    /**
     * @return 完整格式页码导航条
     */

    public String getWholeFormat() {
    	if(pageCount== 1){
    		return "";
    	}
        Html table = new Html("table");
        table.addAttribute("class", "pager");
        Html tr = new Html("tr");

        Html td1 = new Html("td");
        td1.addAttribute("class", "left");
        td1.addContent("共有记录" + resultCount + "条，分为" + pageCount + "页。", false);

        Html td2 = new Html("td");
        td2.addAttribute("class", "center");
//        td2.addContent(getInputNav(), false);

        Html td3 = new Html("td");
        td3.addAttribute("class", "right");
        td3.addContent(getSimpleNav(), false);

        table.addHtml(tr.addHtml(td1).addHtml(td2).addHtml(td3));

        StringBuffer re = new StringBuffer();

        re.append(table);
        re.append(getInputHidden());
        re.append(getScript());

        return re.toString();
    }

    private Html getPageSizeSelect() {

        int[] sizes = new int[]{10, DEFAULT_PAGE_SIZE, 50};

        Html select = new Html("select");
        select.addAttribute("onchange", "pageSizeOnchange('" + formName + "', this.value)");

        for (int size : sizes) {
            Html option = new Html("option");
            option.addAttribute("value", Integer.toString(size));
            if (pageSize == size) {
                option.addAttribute("selected");
            }
            option.addContent(Integer.toString(size));
            select.addHtml(option);
        }

        return select;
    }

    private Html getPageSelect() {

        Html select = new Html("select");
        select.addAttribute("onchange", "pageGoto('" + formName + "', this.value)");

        for (int i = 1; i <= pageCount; i++) {
            Html option = new Html("option");
            option.addAttribute("value", Integer.toString(i));
            if (page == i) {
                option.addAttribute("selected");
            }
            option.addContent(Integer.toString(i));
            select.addHtml(option);
        }

        return select;
    }

    /**
     * @return Select格式页码导航条
     */
    private String getSelectNav() {

        StringBuffer re = new StringBuffer();
        re.append("每页");
        re.append(getPageSizeSelect());
        re.append("条，");
        re.append("第");
        re.append(getPageSelect());
        re.append("页");

        return re.toString();
    }

    private String getInputNav() {
        Html input = new Html("input");
        input.addAttribute("type", "text");
        input.addAttribute("value", String.valueOf(getPage()));
        input.addAttribute("size", "5");
        input.addAttribute("onchange", "pageGoto('" + this.getFormName() + "',this.value)");
        input.addAttribute("onkeydown", "if(13==event.keyCode){pageGoto('" + this.getFormName() + "', this.value)}");

        StringBuffer re = new StringBuffer();
        re.append("第");
        re.append(input.toString());
        re.append("页");
        return re.toString();
    }

    /**
     * @return 简单格式页码导航条
     */

    private String getSimpleNav() {

        StringBuffer re = new StringBuffer();

        if (page > 1) {
            re.append(
                    new Html("a")
                            .addAttribute("href", "javascript:pageGoto('" + formName + "'," + 1 + ")")
                            .addContent("首页")
            ).append(" | ");
        } else {
            re.append("首页 | ");
        }
        if (page > 1) {
            re.append(
                    new Html("a")
                            .addAttribute("href", "javascript:pageGoto('" + formName + "'," + (page - 1) + ")")
                            .addContent("上一页")
            ).append(" | ");
        } else {
            re.append("上一页 | ");
        }
        if (page < pageCount) {
            re.append(
                    new Html("a")
                            .addAttribute("href", "javascript:pageGoto('" + formName + "'," + (page + 1) + ")")
                            .addContent("下一页")
            ).append(" | ");
        } else {
            re.append("下一页 | ");
        }
        if (page < pageCount) {
            re.append(
                    new Html("a")
                            .addAttribute("href", "javascript:pageGoto('" + formName + "'," + pageCount + ")")
                            .addContent("末页")
            ).append("");
        } else {
            re.append("末页");
        }

        return re.toString();
    }

    private String getScript() {
        String content = "\nfunction orderBy(formName, column, direction) {\n" +
                "    var pagerForm = document.forms[formName];\n" +
                "    var orderFieldName = \"orders[\" + column + \"]\";\n" +
                "    \n" +
                "    var el = document.createElement(\"input\");\n" +
                "    el.name = orderFieldName;\n" +
                "    el.type = \"hidden\";\n" +
                "    el.value = direction;\n" +
                "    \n" +
                "    pagerForm.appendChild(el);\n" +
                "\n" +
                "    pagerForm.submit();\n" +
                "}\n" +
                "\n" +
                "function pageSizeOnchange(formName, pageSizeNum) {\n" +
                "    var pagerForm = document.forms[formName];\n" +
                "    pagerForm[\"pageSize\"].value = pageSizeNum;\n" +
                "    pagerForm.submit();\n" +
                "}\n" +
                "\n" +
                "function pageGoto(formName, pageNum) {\n" +
                "    var pagerForm = document.forms[formName];\n" +
                "    pagerForm[\"page\"].value = pageNum;\n" +
                "    pagerForm.submit();\n" +
                "}\n";

        Html script = new Html("script");
        script.addAttribute("type", "text/javascript");
        script.addAttribute("language", "JavaScript");
        script.addContent(content, false);

        return script.toString();
    }


    @Override
    public String toString() {
        return getWholeFormat();
    }

    public String getSimpleFormat() {
        Html table = new Html("table");
        Html tr = new Html("tr");

        Html td1 = new Html("td");
        td1.addAttribute("class", "left");
        td1.addContent("共" + resultCount + "条，分" + pageCount + "页。", false);

        Html td2 = new Html("td");
        td2.addContent("第" + page + "页", false);

        Html td3 = new Html("td");
        td3.addContent(getSimpleNav(), false);

        table.addHtml(tr.addHtml(td1).addHtml(td2).addHtml(td3));

        StringBuffer re = new StringBuffer();

        re.append(table);
        re.append(getScript());

        return re.toString();
    }
    
    public String getInputHidden(){
    	String h1 ="<input type=\"hidden\" name=\"page\" value=\"" + getPage()+ "\"/>";
        String h2 ="<input type=\"hidden\" name=\"pageSize\" value=\"" + getPageSize()+ "\"/>";
        return h1 + h2;
    }

    /**
     * 在查询中使用
     * @param demand
     */
	public void setDemand(Map<String, String> demand) {
		this.demand = demand;
	}

}