package stevemerollis.codetrial.weather.offers.logic

import stevemerollis.codetrial.weather.BaseTest
import org.junit.jupiter.api.*

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class OfferExtensionsTest: BaseTest() {

//    @MockK val offer: Offer = mockk<Offer>()
//    @MockK val intent: Intent = mockk<Intent>()
//    @MockK val extras: Bundle = mockk<Bundle>()
//    val subject: OfferExtensions = OfferExtensionsImpl //stateless kotlin object, no need to refresh
//
//    @Nested
//    @DisplayName("Given hasValidsArgs requires both offerId and offerSource non-null")
//    inner class HasValidArgsResult {
//
//        @Test
//        @DisplayName("When both are non-empty, hasValidArgs")
//        fun testBothNonEmpty() {
//            init()
//            every { offer.intent } returns intent
//            every { intent.offerId  } answers { nonEmptyString }
//            every { intent.offerSource  } answers { nonEmptyString }
//            //assertTrue(offer.hasValidArgs)
//            clearMocks(offer)
//        }
//
//        @Nested
//        @DisplayName("When offerId is null")
//        inner class OfferIdIsNull(source: String?) {
//
//            @BeforeEach
//            fun setup() {
//                init()
//                every { offer.intent } returns intent
//                every { intent.offerId  } answers { null }
//            }
//
//            @AfterEach
//            fun teardown() {
//                clearMocks(offer)
//            }
//
//            @Test
//            @NullAndEmptySource
//            @ValueSource(strings = ["non-empty string"])
//            @DisplayName("Then offerSource valid or invalid, false")
//            fun `test offerSource valid`(answer: String?) {
//                with(OfferExtensionsImpl) {
//                    every { intent.offerSource } answers { answer }
//                    //assertFalse(offer.hasValidArgs)
//                }
//            }
//        }
//
//        @Nested
//        @DisplayName("When offerId is empty")
//        inner class OfferIdIsEmpty {
//
//            @BeforeEach
//            fun setup() {
//                init()
//                every { offer.intent } returns intent
//                every { intent.offerId  } answers { "" }
//            }
//
//            @AfterEach
//            fun teardown() {
//                clearMocks(offer)
//            }
//
//            @Test
//            @NullAndEmptySource
//            @ValueSource(strings = ["non-empty string"])
//            @DisplayName("Then offerSource valid or invalid, false")
//            fun `test offerSource valid`(answer: String?) {
//                with(OfferExtensionsImpl) {
//                    every { intent.offerSource } answers { answer }
//                   // assertFalse(offer.hasValidArgs)
//                }
//            }
//        }
//
//        @Nested
//        @DisplayName("When offerSource is null")
//        inner class OfferSourceIsNull {
//
//            @BeforeEach
//            fun setup() {
//                init()
//                every { offer.intent } returns intent
//                every { intent.offerSource  } answers { null }
//            }
//
//            @AfterEach
//            fun teardown() {
//                clearMocks(offer)
//            }
//
//            @Test
//            @NullAndEmptySource
//            @ValueSource(strings = ["non-empty string"])
//            @DisplayName("Then offerId valid or invalid, false")
//            fun `test offerSource valid`(answer: String?) {
//                with(OfferExtensionsImpl) {
//                    every { intent.offerId } answers { answer }
//                    //assertFalse(offer.hasValidArgs)
//                }
//            }
//        }
//
//        @Nested
//        @DisplayName("When offerSource is empty")
//        inner class OfferSourceIsEmpty {
//
//            @BeforeEach
//            fun setup() {
//                init()
//                every { offer.intent } returns intent
//                every { intent.offerSource  } answers { null }
//            }
//
//            @AfterEach
//            fun teardown() {
//                clearMocks(offer)
//            }
//
//            @Test
//            @NullAndEmptySource
//            @ValueSource(strings = ["non-empty string"])
//            @DisplayName("Then offerId valid or invalid, false")
//            fun `test offerSource valid`(answer: String?) {
//                with(OfferExtensionsImpl) {
//                    every { intent.offerId } answers { answer }
//                    //assertFalse(offer.hasValidArgs)
//                }
//            }
//        }
//
//    }
//
//    @Nested
//    @DisplayName("Given hasCustomTerms requires preface, body, and required arguments")
//    inner class HasCustomTermsResult {
//
//        @Test
//        @DisplayName("When preface non-empty, body non-empty, intent has extra present for required")
//        fun testAllThreeValid() {
//            init()
//            every { offer.intent } returns intent
//            every { intent.termsBody  } answers { nonEmptyString }
//            every { intent.termsPreface  } answers { nonEmptyString }
//            every { intent.termsRequired  } answers { true }
//            //assertTrue(offer.hasValidArgs)
//            clearMocks(offer)
//        }
//
//        @Nested
//        @DisplayName("When preface is null or empty")
//        inner class OneInvalidMember {
//
//            @BeforeEach
//            fun setup() {
//                init()
//                every { offer.intent } returns intent
//                every { intent.termsPreface  } answers { nonEmptyString }
//                every { intent.termsRequired  } answers { true }
//            }
//
//            @AfterEach
//            fun teardown(){
//                clearMocks(offer)
//            }
//
//            @Test
//            @NullAndEmptySource
//            @ValueSource(strings = ["non-empty string"])
//            @DisplayName("body valid or invalid, false")
//            fun `test regardless of content`(answer: String?) {
//                with(OfferExtensionsImpl) {
//                    every { intent.termsPreface } answers { answer }
//                    //assertFalse(offer.hasValidCustomTerms)
//                }
//            }
//
//            @Test
//            @NullSource
//            @ValueSource(booleans = [true])
//            @DisplayName("Then required valid or invalid, false")
//            fun `test regardless of required`(answer: Boolean?) {
//                with(OfferExtensionsImpl) {
//                    every { intent.termsRequired } answers { answer }
//                    //assertFalse(offer.hasValidCustomTerms)
//                }
//            }
//        }
//
//    }


//
//    val Offer.hasValidCustomTerms: Boolean
//
//    val Offer.hasOfferSourceATandT: Boolean
//
//    val Offer.customTerms: TermsOfPurchase?
//
//    fun Offer.asQuoteRequestBody(): QuoteRequestEntity
//
//    fun Offer.asEntity(): OfferDbEntity

}