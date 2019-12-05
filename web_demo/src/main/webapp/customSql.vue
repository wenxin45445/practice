<template>
    <caf-container>
        <caf-main :split="false"  >
            <caf-form ref="queryForm"  label-width="80px" :model="queryForm">
                <caf-header>自定义Sql执行</caf-header>
                <!-- 查询Sql表单 -->
                <caf-layout-line>
                    <caf-form-input type="textarea" label-width="140px" input-width="800px" label="自定义Sql:" v-model="customSql"></caf-form-input>
                </caf-layout-line>
                <caf-layout-line>
                    <caf-button style="margin-left: 60px;margin-top: 8px" @click="onExecuteSqlQry">查询</caf-button>
                    <caf-button style="margin-left: 20px;margin-top: 8px" @click="onExecuteSqlUpdate">更新(DDL/DML)</caf-button>
                    <caf-button style="margin-left: 20px;margin-top: 8px" @click="onExecuteSqlInsert">插入</caf-button>
                    <caf-button style="margin-left: 20px;margin-top: 8px" @click="onExecuteSqlDel">删除</caf-button>
                    <caf-button style="margin-left: 20px;margin-top: 8px" @click="onSaveSql" disabled>保存为</caf-button>
                </caf-layout-line>
                <!-- sql保存历史 -->
                <caf-layout-line>
                    <caf-form-input label-width="140px" input-width="800px" label="已保存Sql:" v-model="customSqlHistory" type="text" @autocompleteerror="" disabled></caf-form-input>
                </caf-layout-line>
            </caf-form>
            <!-- 自定义Sql查询结果 -->
            <caf-table :showPagination=false :header="customSqlHeader" :dataSource="customSqlQryRes" ref="table" tableHeight="400px" >
                <caf-table-column v-for="(item,index) in customSqlHeader" :key="index" v-if="index>0" :label="item.name">

                </caf-table-column>
            </caf-table>
            <!-- 结果分页按钮 -->
            <div style="width: 100%; background: white">
                <caf-pagination style="margin-right:60px;" @size-change="handleSizeChange" @current-change="handleCurrentChange" :current-page="pageNum" :page-size="pageSize" layout="total, sizes, prev, pager, next, jumper" :total="total"></caf-pagination>
            </div>
        </caf-main>
        <!-- 保存对话框 -->
        <caf-dialog :title="dialogTitle" :visible.sync="saveSqlDialog" width="450px" :show-close="false">
            <formTable @backParent="backParent" :item="item"></formTable>
            <span slot="footer" class="dialog-footer"></span>
        </caf-dialog>
    </caf-container>
</template>
<script>
    import formTable from "./formTable";
    import MixinList from "caf-admin-web/lib/pm/mixin/list.js";
    import axios from "axios";
    const CryptoJS = require("crypto-js");
    const tripleDes = require("crypto-js/tripledes");
    export default {
        mixins: [MixinList],
        // 数据项
        data(){
            return{
                customSql:'',
                customSqlHistory:'',
                item:'',//传递给子组件的修改对象
                dialogTitle:'保存Sql',
                queryForm:{
                },
                // 查询到的自定义Sql列表
                customSqlList:[],
                customSqlHeader:[],
                saveSqlDialog:false,
                pageNum:1,
                pageSize:10,
                total:0,
                formTable,
                customSqlQryRes:{},
            }
        },
        // 监听
        watch:{
            // 先注册监听器，监听已保存sql文本框的变化，动态查询候选列表
            'queryForm.customSqlHistory': function (val) {
                // this.savedSqlQry(val);
                console.log(val);
            },
        },
        // 组件
        components: {
            // 注册保存页面组件
            formTable
        },
        // 注册所需函数
        methods:{
            // 执行sql
            onExecuteSqlQry:function () {
                if(this.customSql == ''){
                    this.$message.warning("请输入执行sql!");
                }else{
                    let afterDES = this.enCryptSql();
                    let param = {
                        "customSql": afterDES,
                        "pageNum": this.pageNum,
                        "pageSize": this.pageSize
                    };
                    axios.post('/api/customSql/customSqlQry',param).then((result =>{
                        if(result.data.code == 1){
                            this.customSqlQryRes = {};
                            if(result.data.data.list.length > 0){
                                this.genCustomSqlHeader(result.data.data.list.shift());
                                this.customSqlQryRes.list = result.data.data.list;
                            }
                            this.total = result.data.data.total;
                        }else{
                            this.$message.warning("查询失败," + result.data.message);
                        }
                    }));
                }
            },
            onExecuteSqlUpdate:function () {
                if(this.customSql == ''){
                    this.$message.warning("请输入执行sql!");
                }else{
                    let afterDES = this.enCryptSql();
                    let param = {
                        "customSql": afterDES,
                        "pageNum": this.pageNum,
                        "pageSize": this.pageSize
                    };
                    axios.post('/api/customSql/customSqlUpdate',param).then((result =>{
                        if(result.data.code == 1){
                            this.customSqlQryRes = {};
                            if(result.data.data.list.length > 0){
                                this.genCustomSqlHeader(result.data.data.list.shift());
                                this.customSqlQryRes.list = result.data.data.list;
                            }
                            this.total = result.data.data.total;
                        }else{
                            this.$message.warning("更新失败," + result.data.message);
                        }
                    }));
                }
            },
            onExecuteSqlInsert:function () {
                if(this.customSql == ''){
                    this.$message.warning("请输入执行sql!");
                }else{
                    let afterDES = this.enCryptSql();
                    let param = {
                        "customSql": afterDES,
                        "pageNum": this.pageNum,
                        "pageSize": this.pageSize
                    };
                    axios.post('/api/customSql/customSqlInsert',param).then((result =>{
                        if(result.data.code == 1){
                            this.customSqlQryRes = {};
                            if(result.data.data.list.length > 0){
                                this.genCustomSqlHeader(result.data.data.list.shift());
                                this.customSqlQryRes.list = result.data.data.list;
                            }
                            this.total = result.data.data.total;
                        }else{
                            this.$message.warning("插入失败," + result.data.message);
                        }
                    }));
                }
            },
            onExecuteSqlDel:function () {
                if(this.customSql == ''){
                    this.$message.warning("请输入执行sql!");
                }else{
                    let afterDES = this.enCryptSql();
                    let param = {
                        "customSql": afterDES,
                        "pageNum": this.pageNum,
                        "pageSize": this.pageSize
                    };
                    axios.post('/api/customSql/customSqlDel',param).then((result =>{
                        if(result.data.code == 1){
                            this.customSqlQryRes = {};
                            if(result.data.data.list.length > 0){
                                this.genCustomSqlHeader(result.data.data.list.shift());
                                this.customSqlQryRes.list = result.data.data.list;
                            }
                            this.total = result.data.data.total;
                        }else{
                            this.$message.warning("删除失败," + result.data.message);
                        }
                    }));
                }
            },
            genCustomSqlHeader:function(headerMap){
                // 计算列宽
                // let headWidth = Math.round(1 / Object.keys(headerMap).length * 10000) / 100.00 + "%";
                // 清空，写入列头
                this.customSqlHeader = [];
                for (let headKey in headerMap){
                    let headTemp = {};
                    headTemp.name = headKey;
                    headTemp.column = headKey;
                    headTemp.width = "auto";
                    this.customSqlHeader.push(headTemp)
                }
            },
            enCryptSql: function extracted() {
                let keyHex = CryptoJS.enc.Utf8.parse('com.cpic.gibms');
                let encrypted = CryptoJS.DES.encrypt(this.customSql, keyHex, {
                    mode: CryptoJS.mode.ECB,
                    padding: CryptoJS.pad.Pkcs7
                });
                return encrypted.toString();
            },
            //  获取已保存的sql列表
            savedSqlQry:function(sqlStr, sqlName){

            },
            // 保存sql对话框
            onSaveSql:function () {
                this.dialogTitle = '保存Sql';
                this.item = '';
                this.saveSqlDialog = true;
            },
            // 返回父组件的回调
            backParent:function () {
                this.saveSqlDialog = false;
                let param = {
                    "customSql": this.customSql,
                    "pageNum":this.pageNum,
                    "pageSize":this.pageSize
                };
                //this.onExecuteSql(param);
            },
            // 页码变化数据请求
            handleSizeChange(val) {
                this.pageSize = val;
                this.onExecuteSqlQry();
            },
            // 当前页码数据请求
            handleCurrentChange(val) {
                this.pageNum = val;
                this.onExecuteSqlQry();
            }
        },
        created: async function() {
            // 节点created完成后执行 暂不需要
            //this.$store.dispatch(this.storeModule + "/setTableHeader");
        },
        mounted(){
            // 页面加载完成需要初始化的数据， 暂不需要
            /*let param = {
                "pageNum":this.pageNum,
                "pageSize":this.pageSize
            };
            this.getSalesData(param);*/
        }
    }
</script>