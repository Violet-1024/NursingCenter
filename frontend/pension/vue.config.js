/**
 * 配置参考: https://cli.vuejs.org/zh/config/
 */
module.exports = {
    // publicPath: process.env.NODE_ENV === 'production' ? './' : '/',
    publicPath: '/', //重点，生产环境的路径也改成 '/' 即可
    // eslint效验，在非生产环境为校验
    lintOnSave: process.env.NODE_ENV !== 'production',
    productionSourceMap: false,
    devServer: {
        port: 8888,
        overlay: {
            errors: true,
            warnings: true
        }
    }
}