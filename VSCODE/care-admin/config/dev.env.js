'use strict'
const merge = require('webpack-merge')
const prodEnv = require('./prod.env')


module.exports = merge(prodEnv, {
  NODE_ENV: '"development"',
  BASE_API: '"http://localhost:9000"',
  OSS_PATH: '"https://care-file.oss-cn-shanghai.aliyuncs.com"'
})
