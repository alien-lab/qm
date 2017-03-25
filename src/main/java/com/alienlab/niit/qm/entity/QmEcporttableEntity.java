package com.alienlab.niit.qm.entity;

/**
 * Created by Master QB on 2017/3/14.
 */
@javax.persistence.Entity
@javax.persistence.Table(name = "qm_ecporttable", schema = "qualitymonitor", catalog = "")
public class QmEcporttableEntity {
    private int exportId;

    @javax.persistence.Id
    @javax.persistence.Column(name = "export_id")
    public int getExportId() {
        return exportId;
    }

    public void setExportId(int exportId) {
        this.exportId = exportId;
    }

    private String exportName;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "export_name")
    public String getExportName() {
        return exportName;
    }

    public void setExportName(String exportName) {
        this.exportName = exportName;
    }

    private String templatePath;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "template_path")
    public String getTemplatePath() {
        return templatePath;
    }

    public void setTemplatePath(String templatePath) {
        this.templatePath = templatePath;
    }

    private String exportSql;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "export_sql")
    public String getExportSql() {
        return exportSql;
    }

    public void setExportSql(String exportSql) {
        this.exportSql = exportSql;
    }

    private String exportSqlmore1;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "export_sqlmore1")
    public String getExportSqlmore1() {
        return exportSqlmore1;
    }

    public void setExportSqlmore1(String exportSqlmore1) {
        this.exportSqlmore1 = exportSqlmore1;
    }

    private String exportSqlmore2;

    @javax.persistence.Basic
    @javax.persistence.Column(name = "export_sqlmore2")
    public String getExportSqlmore2() {
        return exportSqlmore2;
    }

    public void setExportSqlmore2(String exportSqlmore2) {
        this.exportSqlmore2 = exportSqlmore2;
    }

    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        if (!super.equals(object)) return false;

        QmEcporttableEntity that = (QmEcporttableEntity) object;

        if (exportId != that.exportId) return false;
        if (exportName != null ? !exportName.equals(that.exportName) : that.exportName != null) return false;
        if (templatePath != null ? !templatePath.equals(that.templatePath) : that.templatePath != null) return false;
        if (exportSql != null ? !exportSql.equals(that.exportSql) : that.exportSql != null) return false;
        if (exportSqlmore1 != null ? !exportSqlmore1.equals(that.exportSqlmore1) : that.exportSqlmore1 != null)
            return false;
        if (exportSqlmore2 != null ? !exportSqlmore2.equals(that.exportSqlmore2) : that.exportSqlmore2 != null)
            return false;

        return true;
    }

    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + exportId;
        result = 31 * result + (exportName != null ? exportName.hashCode() : 0);
        result = 31 * result + (templatePath != null ? templatePath.hashCode() : 0);
        result = 31 * result + (exportSql != null ? exportSql.hashCode() : 0);
        result = 31 * result + (exportSqlmore1 != null ? exportSqlmore1.hashCode() : 0);
        result = 31 * result + (exportSqlmore2 != null ? exportSqlmore2.hashCode() : 0);
        return result;
    }
}
