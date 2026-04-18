module.exports = {
  devServer: {
    proxy: {
      // Este proxy SOLO funciona en localhost
      '/api': {
        target: 'http://localhost:8081',
        changeOrigin: true,
        secure: false,
      },
      '/auth': {
        target: 'http://localhost:8081',
        changeOrigin: true,
        secure: false,
      },
    },
  },
};
