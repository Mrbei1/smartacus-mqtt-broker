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
                style="width: 100%;text-align:center;">
                <el-table-column
                    prop="customerName"
                    label="账号"
                    fixed
                    width="200">
                </el-table-column>
                <el-table-column
                    prop="mobile"
                    label="手机"
                    width="auto">
                </el-table-column>
                <el-table-column
                    prop="wechatOpenid"
                    label="微信openid"
                    width="auto">
                </el-table-column>
                <el-table-column
                    prop="amount"
                    label="账户余额（元）"
                    width="auto">
                </el-table-column>
                <el-table-column
                    prop="spendAmount"
                    label="消费金额（元）"
                    width="auto">
                </el-table-column>
                <el-table-column
                    prop="orderCount"
                    label="订单数"
                    width="auto">
                </el-table-column> <el-table-column
                    prop="accumulatePoint"
                    label="积分"
                    width="200"
                    fixed="right">
                </el-table-column>

                <!-- <el-table-column
                    label="操作"
                    fixed="right"
                    width="100">
                    <template slot-scope="scope">
                        <el-button type="text" icon="el-icon-info" size="small" @click="accountEdit(scope.$index, scope.row)">
                            查看
                        </el-button>

                    </template>
                </el-table-column> -->
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