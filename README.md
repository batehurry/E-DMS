E-DMS
=====

Document Management System (Döküman Yönetim Sistemi)

Gebze Yüksek Teknoloji Enstitüsü<br>
BIL 495 Bitirme Çalışması I Projesi

SİSTEM
======
Kullanıcı:
1. Kullanıcı sisteme üye olabilir. 
2. Üye olduktan sonra yetkili bir kişi tarafından onaylanırsa sistemi kullanmaya başlayabilir.
3. Onaylanan kullanıcı sisteme giriş yapabilir. 
4. Dokuman paylaşımında bulunabilir.
  4.1. Dokuman paylaşımı yapılır iken kullanıcı dokuman'a ait deadline, etiketler, açıklama, dokuman durumu ve hangi gruplar ile paylaşımda bulunduğununu belirtmek zorundadır.
    4.1.1 Grup belirtmez ise sadece kendi grubunda bulunan kişiler dokumanı görebilir. 
    4.1.2 Grup Belirtir ise paylaşımda bulunduğu gruba ait grup hiyerarsisinde üst grupta bulunan herkes ile paylaşımda bulunmuş olur.
5. Kullanıcı Paylaştığı dokümanları veya kendisi ile paylaşılan dokümanları etiket veya başlık ile arayabilir.
6. Kullanıcı Paylaştığı dokümanları veya kendisi ile paylaşılan dokümanları indirebilir, görebilir ve yorum ekleyebilir.
  6.1. Kullanıcı kendi yazdığı yorumları silebilir. 
7. Kullanıcı profilini ve şifresini değiştirebilir.

Yetkili:
1. Sisteme giriş yapabilir.
2. Tüm sistem kullanıcılarını ve onaylanmayı bekleyen kullanıcıları silebilir, düzeltebilir ve onaylayabilir.
3. Kendisi ile paylaşılan veya sistemde bulunan tüm dokümanları indirebilir, görebilir, silebilir, düzeltebilir ve arşivleyebilir.
  3.1. Dokümanlara yorum ekleyebilir.
  3.2. Tüm kullanıcılara ait yorumları silebilir.
4. Dokuman paylaşımında bulunabilir.
  4.1. Dokuman paylaşımı yapılır iken yetkili dokuman'a ait deadline, etiketler, açıklama, dokuman durumu ve hangi gruplar ile paylaşımda bulunduğununu belirtmek zorundadır.
    4.1.1 Grup belirtmez ise sadece kendi grubunda bulunan kişiler dokumanı görebilir. 
    4.1.2 Grup Belirtir ise paylaşımda bulunduğu gruba ait grup hiyerarsisinde üst grupta bulunan herkes ile paylaşımda bulunmuş olur.
5. Yetkili Paylaştığı dokümanları veya kendisi ile paylaşılan dokümanları etiket veya başlık ile arayabilir.
6. Deadline da tamamlanamayan dokümanları görebilir, silebilir, düzeltebilir veya arşivleyebilir.
7. Arşivlenen dokümanları görebilir, düzeltebilir veya silebilir.
8. Yetkili profilini ve şifresini değiştirebilir.

VERSİYON 1
==========
Kullanılan Teknolojiler
1. Mysql
2. JPA
3. Primefaces (bootstrap theme)
4. JSF
