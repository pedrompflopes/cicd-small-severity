 class BufChannelBufferTest extends FunSuite with BeforeAndAfter { 
  
  before {
    buffer = BufChannelBufferFactory(ByteOrder.BIG_ENDIAN).getBuffer(CAPACITY)
    seed = System.currentTimeMillis()
    random = new Random(seed)
  }

  after {
    buffer = null
  }
  
  test("byte array transfer") {
    val bytes = new Array[Byte](CAPACITY)
    val wrapped = ChannelBuffers.wrappedBuffer(bytes)
    val value = new Array[Byte](BLOCK_SIZE * 2)
    0.until(CAPACITY - BLOCK_SIZE + 1, BLOCK_SIZE) foreach { i =>
      random.nextBytes(value)
      wrapped.setBytes(i, value, random.nextInt(BLOCK_SIZE), BLOCK_SIZE)
    }
    val bcb = new BufChannelBuffer(Buf.ByteArray.Owned(bytes))

   random.setSeed(seed)
    val expectedValue = new Array[Byte](BLOCK_SIZE * 2)
    0.until(CAPACITY - BLOCK_SIZE + 1, BLOCK_SIZE) foreach { i =>
      random.nextBytes(expectedValue)
      val valueOffset = random.nextInt(BLOCK_SIZE)
      bcb.getBytes(i, value, valueOffset, BLOCK_SIZE)
      valueOffset.until(valueOffset + BLOCK_SIZE) foreach { j =>
        assertEquals(expectedValue(j), value(j))
      }
    }
	
  }
  
   private[this] def secret(length: Int): Array[Char] = {
    val rng = new Random()
    rng.setSeed(System.currentTimeMillis())
    val b = new Array[Char](length)
     for (i <- 0 until length)
      b(i) = (65 + rng.nextInt(90 - 65)).toChar
     b
  }
  
 }