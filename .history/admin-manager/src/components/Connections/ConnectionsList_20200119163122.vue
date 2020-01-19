<template>
    <div>
        <!-- <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-pie-chart"></i>新建分类</el-breadcrumb-item>
            </el-breadcrumb>
        </div> -->
        <div class="container">
            <el-table
                :data="tableData"
                style="width: 100%">
                <el-table-column
                    prop="customerName"
                    label="账号"
                    fixed
                    width="120">
                </el-table-column>
                <!--<el-table-column
                    label="账号类型"
                    width="150">
                    <template slot-scope="scope">
                        <span>{{scope.row.userType===1?'root':scope.row.userType===2?'代理商':scope.row.userType===3?'普通用户':'未知'}}</span>
                    </template>
                </el-table-column>-->
                <!--<el-table-column
                    label="头像"
                    width="200">
                    <template slot-scope="scope">
                        <img style="width: 150px" v-if="scope.row.avatar" :src="scope.row.avatar"/>
                    </template>
                </el-table-column>-->
                <el-table-column
                    prop="mobile"
                    label="手机"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="wechatOpenid"
                    label="微信openid"
                    width="200">
                </el-table-column>
                <!--<el-table-column
                    prop="alipayOpenid"
                    label="支付宝openid"
                    width="200">
                </el-table-column>-->
                <el-table-column
                    prop="amount"
                    label="账户余额（元）"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="spendAmount"
                    label="消费金额（元）"
                    width="120">
                </el-table-column>
                <el-table-column
                    prop="orderCount"
                    label="订单数"
                    width="auto">
                </el-table-column> <el-table-column
                    prop="accumulatePoint"
                    label="积分"
                    width="auto">
                </el-table-column>

                <!--<el-table-column
                    label="状态"
                    fixed="right"
                    width="auto">
                    <template slot-scope="scope">
                        <span>{{scope.row.status===0?'禁用':'正常'}}</span>
                    </template>
                </el-table-column>-->
                <el-table-column
                    label="操作"
                    fixed="right"
                    width="100">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-info" size="small" @click="accountEdit(scope.$index, scope.row)">
                            查看
                        </el-button>
                        <!--<el-button type="text" icon="el-icon-delete" style="color:red;" size="small" @click="handleDelete(scope.$index,scope.row)"-->
                                   <!--v-if="(nodeNames.indexOf('account_del')!=-1)">删除-->
                        <!--</el-button>-->

                    </template>
                </el-table-column>
            </el-table>
            <el-pagination
                background
                layout="prev, pager, next"
                @current-change="handleCurrentChange"
                :current-page="pageNo"
                :page-size="pageSize"
                :total="totalRows">
            </el-pagination>
        </div>
    </div>
</template>
<script>
    export default{
        data(){
            return {
                title:'',
                editVisible: false,
                // delVisible:false,
                // getId:'',
                rowObj:{},
                tableData: [],
                nodeNames: [],
                pageNo: 1,
                pageSize: 10,
                totalRows: 0,
            }
        },
        created(){
            this.getCustomerList();
        },
        methods:{
            getCustomerList() {
                let obj = {
                    pageNo: this.pageNo,
                    pageSize: this.pageSize,

                };
                this.$axios.post(this.$Api.getCustomerList, obj).then(response => {
                    if (response.data.code === 200) {
                        console.log(response.data.content)
                        this.tableData = response.data.content.data;
                        this.totalRows = response.data.content.totalRows;
                    }
                })
            },

            // getNodeList() {
            //     let obj = {
            //         params: {
            //             "resourceId": 'account_list'
            //         }

            //     };

            //     this.$axios.get(this.$Api.getSubResourceNodeList, obj).then(response => {
            //         if (response !== "") {
            //             response.data.content.forEach(value => {
            //                 if (this.nodeNames.indexOf(value.page) == -1) {
            //                     this.nodeNames.push(value.page);
            //                 }
            //             })
            //         }

            //     }).catch(error => {
            //     })
            // },
            handleDelete(index, row) {
                this.id = row.id;
                this.delVisible = true;
            },
            // 确定删除
            // deleteRow(){
            //     let obj = {
            //         params:{
            //             id:this.id
            //         }
            //     }
            //     this.$axios.get(this.$Api.DeviceDel,obj).then(response => {
            //         if (response.data.code === 200) {
            //             this.$message.success('删除成功');
            //             this.delVisible = false;
            //             this.DeviceList();
            //         }
            //     });
            // },
            handleCurrentChange(val) {
                this.pageNo = val;
                this.getCustomerList()
            },
        }
    }
</script>