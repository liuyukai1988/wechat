// pages/topic/topic.js
const util = require('../../utils/util.js')

Page({

  /**
   * 页面的初始数据
   */
  data: {
    list: [],
    title: ""
  },
  getTitle: function (e) {
    var that = this;
    var val = e.detail.value;
    that.setData({
      title: val
    });
  },
  // 查询搜索的接口方法
  search: function () {
    var that = this;
    wx.request({
      url: 'http://127.0.0.1:8080/getaqbytitle?title='+that.data.title,
      header: {
          'Content-Type': 'application/json'
      },
      success: function(res) {
        console.log(res.data)
        that.setData({         
          list: res.data  //我要将list这个碗里放入后台请求的is_online=0或者1这些东西        
          });
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    var that = this;
      wx.showLoading({title: '加载中', icon: 'loading', duration: 10000});
      wx.request({
        url: 'http://127.0.0.1:8080/all',
        header: {
            'Content-Type': 'application/json'
        },
        success: function(res) {
          console.log(res.data)
          that.setData({         
            list: res.data  //我要将list这个碗里放入后台请求的is_online=0或者1这些东西        
            });
            wx.hideLoading();
        }
      })
  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {

  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {
    return {
      title: '面试管家',
      desc: '面试宝典',
      path: '/pages/topic/topic'
    }
  }
})