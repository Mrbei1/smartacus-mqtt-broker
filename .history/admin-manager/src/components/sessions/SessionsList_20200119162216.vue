<template>
    <div>
        <!-- <div class="crumbs">
            <el-breadcrumb separator="/">
                <el-breadcrumb-item><i class="el-icon-pie-chart"></i>新建分类</el-breadcrumb-item>
            </el-breadcrumb>
        </div> -->
        <div class="container">
            <el-form label-width="120px" @submit.native.prevent="save">
                <el-form-item label="sessions">
                    <el-input v-model="formData.name"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" native-type="submit">保存</el-button>
                </el-form-item>
            </el-form>
        </div>
    </div>
</template>
<script>
    export default{
        data(){
            return{
                formData:{
                    name:''
                }
            }
        },
        created(){

        },
        methods:{
            save(){
                console.log(222)
                
            }
        }
    }
</script>