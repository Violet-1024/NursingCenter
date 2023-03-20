<template>
  <el-upload
    class="avatar-uploader"
    action="http://121.5.35.178:8081/admin/upload/image"
    name="image"
    :show-file-list="false"
    :on-success="handleAvatarSuccess"
    :before-upload="beforeAvatarUpload"
    :headers="{token:token}"
  >
    <img v-if="imageUrl" :src="imageUrl" class="avatar" />
    <img v-else-if="getURL2" :src="getURL2" class="avatar" />
    <i v-else class="el-icon-plus avatar-uploader-icon"></i>
  </el-upload>
</template>
<style>

.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 140px;
  height: 140px;
  line-height: 140px;
  text-align: center;
}
.avatar {
  width: 140px;
  height: 140px;
  display: block;
}
</style>

<script>
import Bus from "../../EventBus"
export default {
  props:[
    "getURL2"
  ],
  created() {
    this.token=sessionStorage.getItem("token")
  },
  data() {
    return {
      token:"",
      imageUrl: "",
      
    };
  },
  methods: {
    handleAvatarSuccess(res, file) {
      this.imageUrl = res.data;
      this.handlegetimgUrl()
      console.log(res)
    },
     handlegetimgUrl() {
      this.$emit("getimgUrl", this.imageUrl);
    },
    beforeAvatarUpload(file) {
      const isJPG = file.type === "image/jpeg";
      const isPNG = file.type === "image/png";
      let size = file.size / 1024 / 1024 / 2;
      if (size > 2) {
        this.$message.error("上传头像图片大小必须小于2M");
      }
      if (!(isJPG || isPNG)) {
        this.$message.error("上传头像图片只能是 JPG/PNG 格式!");
      }
    },
  },
};
</script>