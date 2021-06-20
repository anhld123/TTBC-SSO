function number_format(number, decimals, dec_point, thousands_sep) {
  //  VD: 1: number_format(1234.56);
  //  Ket qua: 1: '1,235'
  //  VD: 2: number_format(1234.56, 2, ',', ' ');
  //  Ket qua: 2: '1 234,56'
  //  VD: 3: number_format(1234.5678, 2, '.', '');
  //  Ket qua: 3: '1234.57'
  //  VD: 4: number_format(67, 2, ',', '.');
  //  Ket qua: 4: '67,00'
  //  VD: 5: number_format(1000);
  //  Ket qua: 5: '1,000'
  //  VD: 6: number_format(67.311, 2);
  //  Ket qua: 6: '67.31'
  //  VD: 7: number_format(1000.55, 1);
  //  Ket qua: 7: '1,000.6'
  //  VD: 8: number_format(67000, 5, ',', '.');
  //  Ket qua: 8: '67.000,00000'
  //  VD: 9: number_format(0.9, 0);
  //  Ket qua: 9: '1'
  //  VD: 10: number_format('1.20', 2);
  //  Ket qua: 10: '1.20'
  //  VD: 11: number_format('1.20', 4);
  //  Ket qua: 11: '1.2000'
  //  VD: 12: number_format('1.2000', 3);
  //  Ket qua: 12: '1.200'
  //  VD: 13: number_format('1 000,50', 2, '.', ' ');
  //  Ket qua: 13: '100 050.00'
  //  VD: 14: number_format(1e-8, 8, '.', '');
  //  Ket qua: 14: '0.00000001'

  number = (number + '')
    .replace(/[^0-9+\-Ee.]/g, '');
  var n = !isFinite(+number) ? 0 : +number,
    prec = !isFinite(+decimals) ? 0 : Math.abs(decimals),
    sep = (typeof thousands_sep === 'undefined') ? ',' : thousands_sep,
    dec = (typeof dec_point === 'undefined') ? '.' : dec_point,
    s = '',
    toFixedFix = function(n, prec) {
      var k = Math.pow(10, prec);
      return '' + (Math.round(n * k) / k)
        .toFixed(prec);
    };
  // Fix for IE parseFloat(0.55).toFixed(0) = 0;
  s = (prec ? toFixedFix(n, prec) : '' + Math.round(n))
    .split('.');
  if (s[0].length > 3) {
    s[0] = s[0].replace(/\B(?=(?:\d{3})+(?!\d))/g, sep);
  }
  if ((s[1] || '')
    .length < prec) {
    s[1] = s[1] || '';
    s[1] += new Array(prec - s[1].length + 1)
      .join('0');
  }
  return s.join(dec);
}